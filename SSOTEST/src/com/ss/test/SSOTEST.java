package com.ss.test;

import java.util.List;

import com.ss.config.ApplicationDAO;
import com.ss.model.QuestionAns;
import com.ss.model.SkillDetails;
import com.ss.model.UserDetails;

public class SSOTEST {
	public static void main(String[] args) {
		try {

			System.out.println("**************Password validation****************");
			boolean status=ApplicationDAO.validate("sanjeet", "sanjeet");//validation
			System.out.println(status);

			System.out.println("**************user creation****************");
			UserDetails udetails=new UserDetails("sanjeet","sanjeet","ss@gmail.com","90999999","java");
			int userRecord=ApplicationDAO.saveUser(udetails);
			System.out.println(userRecord);



			System.out.println("**************Skill Details List****************");
			List<SkillDetails> skillDetails=ApplicationDAO.viewSkillDetails();
			for (int i=0; i<skillDetails.size(); i++)
				System.out.println(skillDetails.get(i).getSkill());


			System.out.println("**************Save Question  Answer Details****************");
			QuestionAns questionAns=new QuestionAns("what is java ?","Y","N","N","N", "optOne", "optTwo", "optThree", "optFour", "java");
			int questionAnsstatus = ApplicationDAO.saveQuestionAns(questionAns);
			System.out.println(questionAnsstatus);



			System.out.println("**************view all question answer details****************");
			List<QuestionAns> viewQuestionAns=ApplicationDAO.viewQuestionAns();
			if(viewQuestionAns!=null){
				for (int i=0; i<viewQuestionAns.size(); i++){
					System.out.println(viewQuestionAns.get(i).getId()+" "+viewQuestionAns.get(i).getQuestion()+"\n"+viewQuestionAns.get(i).getOptOne()+" "+viewQuestionAns.get(i).getOptTwo()+"   "+viewQuestionAns.get(i).getOptThree()+"   "+viewQuestionAns.get(i).getOptFour());
					System.out.print(viewQuestionAns.get(i).getAnsOne()+"\t"+viewQuestionAns.get(i).getAnsTwo()+"\t"+viewQuestionAns.get(i).getAnsThree()+"\t\t"+viewQuestionAns.get(i).getAnsFour());
					System.out.println("\nSkill:-"+viewQuestionAns.get(i).getSkill());
					System.out.println("\n\n\n\n");
				}
			}


			System.out.println("******************delete records from question answer****************");
			int deltedRecords=ApplicationDAO.deleteQusAns(11);
			System.out.println(deltedRecords);


			System.out.println("******************show edit question answer on particular details************");
			QuestionAns viewQuestionAnss=ApplicationDAO.editQuestionAns(12);
			if(viewQuestionAnss!=null){
				System.out.println(viewQuestionAnss.getId()+" "+viewQuestionAnss.getQuestion()+"\n"+viewQuestionAnss.getOptOne()+" "+viewQuestionAnss.getOptTwo()+"   "+viewQuestionAnss.getOptThree()+"   "+viewQuestionAnss.getOptFour());
				System.out.print(viewQuestionAnss.getAnsOne()+"\t"+viewQuestionAnss.getAnsTwo()+"\t"+viewQuestionAnss.getAnsThree()+"\t\t"+viewQuestionAnss.getAnsFour());
				System.out.println("\nSkill:-"+viewQuestionAnss.getSkill());
				System.out.println("\n\n\n\n");
			}


			System.out.println("******************save updated queswer answer************");
			QuestionAns updatedQuestionAns=new QuestionAns("what is java ?","Y","Y","N","Y", "optOne", "optTwo", "optThree", "optFour", "skill");//QuestionAns creation
			updatedQuestionAns.setId(12);
			updatedQuestionAns.setSkill("Python");
			int updateStatus=ApplicationDAO.saveUpdatedQuestionAns(updatedQuestionAns);
			System.out.println(updateStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
