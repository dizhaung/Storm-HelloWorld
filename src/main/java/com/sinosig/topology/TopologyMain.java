package com.sinosig.topology;

import com.sinosig.blot.AddSomeThingBolt;
import com.sinosig.blot.PrintBolt;
import com.sinosig.spout.RandomSentenceSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.utils.Utils;

/**
 * @ClassName TopologyMain
 * @Description �� ���ĸ�д���࣬����֮ǰ�����нڵ����ߣ�����Ϊ������
 * @author suyu
 * @Date 2018/1/12 17:20
 * @version 1.0.0
 */
public class TopologyMain {

    public static void main(String[] args) throws Exception {
        //1.��������builder
        TopologyBuilder builder = new TopologyBuilder();
        //2.�������߽ڵ㣬��spout����topology��
        builder.setSpout("spout",new RandomSentenceSpout());
        //3.������һ��bolt�ڵ����topology�У�shuffleGrouping��Storm���պ��ֲ��Խ�tuple���䵽������boltȥ��
        builder.setBolt("addBlot", new AddSomeThingBolt(),2).shuffleGrouping("spout");
        //4.�����ڶ���bolt�ڵ����topology��
        builder.setBolt("printBlot", new PrintBolt(),3).shuffleGrouping("addBlot");

        Config conf = new Config();
        conf.setDebug(false);
        //���в�������ִ��storm��Ⱥ�ύ����û�б���ģ�⼯Ⱥ�ύtopology
        if (args != null && args.length > 0) {
            conf.setNumWorkers(3);
            StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
        } else {
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("test", conf, builder.createTopology());
            Utils.sleep(100000);
            cluster.killTopology("test");
            cluster.shutdown();
        }
    }
}
