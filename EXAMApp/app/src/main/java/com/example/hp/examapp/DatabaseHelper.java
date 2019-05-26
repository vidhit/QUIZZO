package com.example.hp.examapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.hp.examapp.ExamContract;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Skillio.db";
    private static final  int DATABASE_VERSION=1;

    private  SQLiteDatabase db;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db=db;
        final String CREATE_TABLE_QUESTIONS = "CREATE TABLE  " +  ExamContract.QuestionsTable.Table_Name + " ( " + ExamContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ExamContract.QuestionsTable.COLUMN_QUESTIONS + " TEXT ," + ExamContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " + ExamContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " + ExamContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " + ExamContract.QuestionsTable.COLUMN_OPTION4 + " TEXT, " + ExamContract.QuestionsTable.COLUMN_OPTION5 + " TEXT, " +ExamContract.QuestionsTable.COLUMN_ANSWERNO + " INTEGER " +")";
        db.execSQL(CREATE_TABLE_QUESTIONS);
        fillwithquestions();

    }

    private void fillwithquestions() {
        Questions q1=new Questions("Which of the following statements should be used to obtain a remainder after dividing 3.14 by 2.1 ?","rem = 3.14 % 2.1;","rem = modf(3.14, 2.1);","rem = fmod(3.14, 2.1);\t@","Remainder cannot be obtain in floating point division.","None of the above",3);
        insertQuestions(q1);
        Questions q2=new Questions("What are the types of linkages?","Internal and External","External, Internal and None","External and None","Internal","None of the above",2);
        insertQuestions(q2);
        Questions q3=new Questions("Which of the following special symbol allowed in a variable name?","* (asterisk)","| (pipeline)","- (hyphen)","_ (underscore)","None",4);
        insertQuestions(q3);
        Questions q4=new Questions("Is there any difference between following declarations?" +
                "extern int fun(); int fun(); ","Both are identical","No difference, except extern int fun(); is probably in another file","int fun(); is overrided with extern int fun();","Both are incorrect declarations","None",2);
        insertQuestions(q4);
        Questions q5=new Questions("How would you round off a value from 1.66 to 2.0?","ceil(1.66)","floor(1.66)","roundup(1.66)","roundto(1.66)","None",1);
         insertQuestions(q5);
        Questions q6=new Questions("E is correct","A","B","C","D","E",5);
        insertQuestions(q6);
        Questions q7=new Questions("E is correct","A","B","C","D","E",5);
        insertQuestions(q7);
        Questions q8=new Questions("E is correct","A","B","C","D","E",5);
        insertQuestions(q8);
        Questions q9=new Questions("E is correct","A","B","C","D","E",5);
        insertQuestions(q9);
        Questions q10=new Questions("E is correct","A","B","C","D","E",5);
        insertQuestions(q10);
        Questions q11=new Questions("E is correct","A","B","C","D","E",5);
        insertQuestions(q11);
        Questions q12=new Questions("E is correct","A","B","C","D","E",5);
        insertQuestions(q12);
        Questions q13=new Questions("E is correct","A","B","C","D","E",5);
        insertQuestions(q13);
        Questions q14=new Questions("E is correct","A","B","C","D","E",5);
        insertQuestions(q14);
        Questions q15=new Questions("E is correct","A","B","C","D","E",5);
        insertQuestions(q15);
        Questions q16=new Questions("E is correct","A","B","C","D","E",5);
        insertQuestions(q16);
        Questions q17=new Questions("E is correct","A","B","C","D","E",5);
        insertQuestions(q17);
        Questions q18=new Questions("E is correct","A","B","C","D","E",5);
        insertQuestions(q18);
        Questions q19=new Questions("E is correct","A","B","C","D","E",5);
        insertQuestions(q19);
        Questions q20=new Questions("E is correct","A","B","C","D","E",5);
        insertQuestions(q20);

    }
    private void insertQuestions(Questions question)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(ExamContract.QuestionsTable.COLUMN_QUESTIONS,question.getQuestion());
        contentValues.put(ExamContract.QuestionsTable.COLUMN_OPTION1,question.getChoice1());
        contentValues.put(ExamContract.QuestionsTable.COLUMN_OPTION2,question.getChoice2());
        contentValues.put(ExamContract.QuestionsTable.COLUMN_OPTION3,question.getChoice3());
        contentValues.put(ExamContract.QuestionsTable.COLUMN_OPTION4,question.getChoice4());
        contentValues.put(ExamContract.QuestionsTable.COLUMN_OPTION5,question.getChoice5());
        contentValues.put(ExamContract.QuestionsTable.COLUMN_ANSWERNO,question.getAnswernr());
        db.insert(ExamContract.QuestionsTable.Table_Name,null,contentValues);
    }
    public List<Questions> getAllquestion()
    {
        List<Questions> questionsList=new ArrayList<>();
        db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM " + ExamContract.QuestionsTable.Table_Name,null);
        if(cursor.moveToFirst())
        {
            do{
                Questions questions=new Questions();
                questions.setQuestion(cursor.getString(cursor.getColumnIndex(ExamContract.QuestionsTable.COLUMN_QUESTIONS)));
                questions.setChoice1(cursor.getString(cursor.getColumnIndex(ExamContract.QuestionsTable.COLUMN_OPTION1)));
                questions.setChoice2(cursor.getString(cursor.getColumnIndex(ExamContract.QuestionsTable.COLUMN_OPTION2)));
                questions.setChoice3(cursor.getString(cursor.getColumnIndex(ExamContract.QuestionsTable.COLUMN_OPTION3)));
                questions.setChoice4(cursor.getString(cursor.getColumnIndex(ExamContract.QuestionsTable.COLUMN_OPTION4)));
                questions.setChoice5(cursor.getString(cursor.getColumnIndex(ExamContract.QuestionsTable.COLUMN_OPTION5)));
                questions.setAnswernr(cursor.getInt(cursor.getColumnIndex(ExamContract.QuestionsTable.COLUMN_ANSWERNO)));
                questionsList.add(questions);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return questionsList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " + ExamContract.QuestionsTable.Table_Name);
      onCreate(db);
    }
}
