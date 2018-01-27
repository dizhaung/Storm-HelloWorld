package com.sinosig.blot;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

/**
 * @author suyu
 * @version 1.0.0
 * @ClassName PrintBolt
 * @Description �۵ڶ�������blot�ڵ�,��ӡblot��������д����
 * @Date 2018/1/12 18:12
 */
public class PrintBolt extends BaseBasicBolt {


    private int indexId;

    /**
     * ��ӡblot
     * @param tuple
     * @param collector
     */
    @Override
    public void execute(Tuple tuple, BasicOutputCollector collector) {
        String printBlot = tuple.getString(0);
        System.err.println(String.format("Bolt[%d] String recieved: %s",this.getIndexId(), printBlot));
//        System.out.println("���յ��ġ���ӡblot��:" + printBlot);
    }

    /**
     * ����������
     * @param declarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    public int getIndexId() {
        this.indexId = (int) Thread.currentThread().getId();
        return indexId;
    }
}
