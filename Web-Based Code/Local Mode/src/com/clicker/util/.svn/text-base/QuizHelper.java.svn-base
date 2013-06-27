package com.clicker.util;

import java.util.Vector;

import com.clicker.wrappers.Option;
import com.clicker.wrappers.Question;

public class QuizHelper {
	public StringBuffer getQuizQuestions(Vector<Question> QuestionList){
		StringBuffer quizQuestions = new StringBuffer();
		for(int i=0;i<QuestionList.size();i++){
			Question quest = new Question();
			quest = QuestionList.get(i);
			quizQuestions.append(quest.getQuestionID() + "@;");
			quizQuestions.append(quest.getQuestionText() + "@;");
			quizQuestions.append(quest.getQuestionType() + "@;");
			Vector<Option> opt_list = new Vector<Option>();
			opt_list = quest.getOptions();
			for(int j=0;j<opt_list.size();j++){
				Option opt = new Option();
				opt = opt_list.get(j);
				quizQuestions.append(opt.getOptionValue() + "@,");
			}
			quizQuestions.append("@@");
		}
		return quizQuestions;
	}
}
