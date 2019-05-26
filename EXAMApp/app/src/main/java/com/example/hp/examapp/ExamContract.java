package com.example.hp.examapp;

import android.provider.BaseColumns;

public final class ExamContract {

    private  ExamContract()
    {

    }

    public static class QuestionsTable implements BaseColumns{

        public static final String Table_Name="exam_questions";
        public static final String COLUMN_QUESTIONS="questions";
        public static final String COLUMN_OPTION1="choice1";
        public static final String COLUMN_OPTION2="choice2";
        public static final String COLUMN_OPTION3="choice3";
        public static final String COLUMN_OPTION4="choice4";
        public static final String COLUMN_OPTION5="choice5";
        public static final String COLUMN_ANSWERNO="answerno";

    }
}
