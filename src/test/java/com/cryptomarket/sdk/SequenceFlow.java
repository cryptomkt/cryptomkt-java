package com.cryptomarket.sdk;

public class SequenceFlow {
    private static Integer sequence = 0;
    
    public static Boolean checkNextSequence(Integer sequence) {
        Boolean goodFlow = true;
        if (sequence != 0 && SequenceFlow.sequence > sequence) goodFlow = false;
        SequenceFlow.sequence = sequence;
        return goodFlow;
    }
}
