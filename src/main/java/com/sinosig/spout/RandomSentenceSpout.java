package com.sinosig.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.Random;

/**
 * @author suyu
 * @version 1.0.0
 * @ClassName RandomSentenceSpout
 * @Description �� ��һ��д����Spout
 *              spout������Դ��Դͷ���Ὣ��"˭":"˵��ʲô"�������ĸ�ʽ���䵽blot��
 *              open������Ҫ��storm��ܴ���SpoutOutputCollector
 *              nextTuple������Ҫ�Ƿ�������Դ
 * @Date 2018/1/12 17:22
 */
public class RandomSentenceSpout extends BaseRichSpout {
    /**
     * �����ռ�Spout�����tuple
     */
    private SpoutOutputCollector collector;
    private Random random;
    private static String[] sentences = new String[] {"edi:I'm happy",
            "marry:I'm angry", "john:I'm sad", "ted:I'm excited", "laden:I'm dangerous" ,"suyu:I'm Hello World"};


    /**
     * �÷�������һ�Σ���Ҫ��storm��ܴ���SpoutOutputCollector
     * @param conf ���岻��
     * @param context ����������
     * @param collector ����Դ���ռ���
     */
    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
        this.random = new Random();
    }

    /**
     * �˷����Ǵ�ISpout�ӿ�ʵ�ֵġ�
     * ���˷���������ʱ��storm����������soupt����Ԫ��������collector���������Ӧ���Ƿ������ģ�
     * �������spoutû��Ԫ�鷢�䣬�������Ӧ�ý��з��ء�
     * When this method is called, Storm is requesting that the Spout emit tuples to the
     * output collector. This method should be non-blocking, so if the Spout has no tuples
     * to emit, this method should return. nextTuple, ack, and fail are all called in a tight
     * loop in a single thread in the spout task. When there are no tuples to emit, it is courteous
     * to have nextTuple sleep for a short amount of time (like a single millisecond)
     * so as not to waste too much CPU.
     */
    @Override
    public void nextTuple() {
        //���Զ������������ȡ��ÿ��˵���ľ���
        String toSay = sentences[random.nextInt(sentences.length)];
        //���䷢���ȥ�������values�Ǽ̳���ArrayList
        this.collector.emit(new Values(toSay));
    }

    /**
     * �÷������������Ƿ����value������Stream�ж���һ���������������Ϊkey����ֵ����������topology������Ψһ��
     * ���������ͼ�������˵�������
     * Declare the output schema for all the streams of this topology.
     * @param declarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("sentence"));
    }
}
