package com.sinosig.blot;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/**
 * @ClassName AddSomeThingBolt
 * @Description �ڵڶ���д�����Ǵ���Ԫ���ݵ�blot��
 * @author suyu
 * @Date 2018/1/12 18:05
 * @version 1.0.0
 */
public class AddSomeThingBolt extends BaseBasicBolt {

    private int indexId;

    /**
     * blot��ִ�з�����ʵ����IBasicBolt�ӿڣ�
     * ��������Ԫ�飬ѡ���Ե��ύ�µ�Ԫ����������Ԫ�飨���˷��룩
     * Process the input tuple and optionally emit new tuples based on the input tuple.
     * All acking is managed for you. Throw a FailedException if you want to fail the tuple.
     * @param tuple  ��spout�з�������ľ��ӣ��������
     * @param collector ����spout �е��ռ�����Ҳ���𵽷��������
     */
    @Override
    public void execute(Tuple tuple, BasicOutputCollector collector) {
        //�õ�tuple�еĵ�һ��Ԫ��
        String sentence = (String) tuple.getValue(0);
        //�����߼�ƴ��
        String out = sentence + "!!!! yoyoyo!!~~";
        //����������з������һ��blot
        collector.emit(new Values(out));
        System.err.println(String.format("--------------AddSomeThingBolt[%d] ------------------",this.getIndexId()));
    }

    /**
     * ����spout�еķ������÷������������Ƿ����value������Stream�ж���һ���������������Ϊkey����ֵ����������topology������Ψһ��
     * @param declarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("add_sentence"));
    }


    public int getIndexId() {
        this.indexId = (int) Thread.currentThread().getId();
        return indexId;
    }
}
