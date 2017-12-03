package com.ss.config;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.model.QuestionAns;
import com.ss.model.SkillDetails;
import com.ss.model.UserDetails;
public class ApplicationDAO {
	public static Connection getCon(){
		Connection con=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin123");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	public static boolean validate(String name,String password){//validating user
		boolean status=false;
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("select * from TestLoginUser where name=? and password=?");
			ps.setString(1,name);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			status=rs.next();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static int saveUser(UserDetails udetails){//saving user by the given method
		int status=0;
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("insert into TestLoginUser(name,password,email,contactno,skill) values(?,?,?,?,?)");
			ps.setString(1,udetails.getName());
			ps.setString(2,udetails.getPassword());
			ps.setString(3,udetails.getEmail());
			ps.setString(4,udetails.getContactno());
			ps.setString(5,udetails.getSkill());
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static int saveQuestionAns(QuestionAns questionAns) throws SQLException{//saving question answer by procedure calling
		int status=0;
		CallableStatement callableStatement = null;
		try{
			Connection con=getCon();
			String saveQuestionAns = "{call TestQuestionAnswer(?,?,?,?,?,?,?,?,?,?)}";
			callableStatement = con.prepareCall(saveQuestionAns);
			callableStatement.setString(1, questionAns.getQuestion());
			callableStatement.setString(2, questionAns.getOptOne());
			callableStatement.setString(3, questionAns.getOptTwo());
			callableStatement.setString(4, questionAns.getOptThree());
			callableStatement.setString(5, questionAns.getOptFour());
			callableStatement.setString(6, questionAns.getAnsOne());
			callableStatement.setString(7, questionAns.getAnsTwo());
			callableStatement.setString(8, questionAns.getAnsThree());
			callableStatement.setString(9, questionAns.getAnsFour());
			callableStatement.setString(10, questionAns.getSkill());
			//callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			status=callableStatement.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);
		}
		finally {

			if (callableStatement != null) {
				callableStatement.close();
			}}
		return status;
	}

	public static List<QuestionAns> viewQuestionAns(){//view all question  answer(join both column)
		List<QuestionAns> list=new ArrayList<>();
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("select tques.*,tans.* from TestQuestion tques inner join TestAnswer tans on tques.id=tans.unid");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				QuestionAns questionAns=new QuestionAns();
				questionAns.setId(rs.getInt(1));
				questionAns.setQuestion(rs.getString(2));
				questionAns.setAnsOne(rs.getString("A"));
				questionAns.setAnsTwo(rs.getString("B"));
				questionAns.setAnsThree(rs.getString("C"));
				questionAns.setAnsFour(rs.getString("D"));
				questionAns.setOptOne(rs.getString("OPTONE"));
				questionAns.setOptTwo(rs.getString("OPTTWO"));
				questionAns.setOptThree(rs.getString("OPTTHREE"));
				questionAns.setOptFour(rs.getString("OPTFOUR"));
				questionAns.setSkill(rs.getString("SKILL"));
				list.add(questionAns);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return list;
	}
	public static int deleteQusAns(int unqid){//delete question answer
		int status=0;
		try{
			Connection con=getCon();
			String sql = "{ ? = call TestQuestionAnsDel(?) }";//procedure function
			CallableStatement cs = con.prepareCall(sql);
			cs.setInt(2,unqid);
			cs.registerOutParameter(1, java.sql.Types.INTEGER);  
			cs.execute(); 
			status= cs.getInt(1);
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static QuestionAns editQuestionAns(int unqid){//Edit question  answer(join both column) and show values on screen 
		QuestionAns quesAns=new QuestionAns();
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("select tques.*,tans.* from TestQuestion tques inner join TestAnswer tans on tques.id=tans.unid and tques.id="+unqid+"");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				//QuestionAns questionAns=new QuestionAns();
				quesAns.setId(rs.getInt(1));
				quesAns.setQuestion(rs.getString(2));
				quesAns.setAnsOne(rs.getString("A"));
				quesAns.setAnsTwo(rs.getString("B"));
				quesAns.setAnsThree(rs.getString("C"));
				quesAns.setAnsFour(rs.getString("D"));
				quesAns.setOptOne(rs.getString("OPTONE"));
				quesAns.setOptTwo(rs.getString("OPTTWO"));
				quesAns.setOptThree(rs.getString("OPTTHREE"));
				quesAns.setOptFour(rs.getString("OPTFOUR"));
				quesAns.setSkill(rs.getString("SKILL"));
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return quesAns;
	}
	//with the help of question id update recent values in question ans table
	public static int saveUpdatedQuestionAns(QuestionAns questionAns){
		int status=0;
		CallableStatement callableStatement = null;
		try{
			Connection con=getCon();
			//procedure calling
			String saveQuestionAns = "{call TestQuestionAnswerUpdate(?,?,?,?,?,?,?,?,?,?,?)}";
			callableStatement = con.prepareCall(saveQuestionAns);
			callableStatement.setInt(1, questionAns.getId());
			callableStatement.setString(2, questionAns.getQuestion());
			callableStatement.setString(3, questionAns.getOptOne());
			callableStatement.setString(4, questionAns.getOptTwo());
			callableStatement.setString(5, questionAns.getOptThree());
			callableStatement.setString(6, questionAns.getOptFour());
			callableStatement.setString(7, questionAns.getAnsOne());
			callableStatement.setString(8, questionAns.getAnsTwo());
			callableStatement.setString(9, questionAns.getAnsThree());
			callableStatement.setString(10, questionAns.getAnsFour());
			callableStatement.setString(11, questionAns.getSkill());
			//callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			status=callableStatement.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static List<SkillDetails> viewSkillDetails(){//view all skill in drop down
		List<SkillDetails> list=new ArrayList<>();
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("select * from TestSkill");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				SkillDetails skillDetails=new SkillDetails();
				skillDetails.setId(rs.getInt(1));
				skillDetails.setSkill(rs.getString(2));
				skillDetails.setDt(rs.getDate(3));
				list.add(skillDetails);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return list;
	}
}
