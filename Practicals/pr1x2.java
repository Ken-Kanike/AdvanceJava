// pgno 2 x2
import java.awt.*;
import java.applet.*;

/* <applet code="pr1x2" width="600" height="600"></applet> */
public class pr1x2 extends Applet 
{
Label lname ,ladd;
TextField tfName;
TextArea tfAdd;
Button  btnSubmit;
Font f;


	public void init()
	{
		setBackground(Color.cyan);
		f = new Font("Arial",Font.BOLD,16);

		lname = new Label("Enter Name:"); lname.setFont(f);
		ladd = new Label("Enter Address:"); ladd.setFont(f); 

		tfName = new TextField(40);  
		tfAdd = new TextArea(5,10); 
		
		btnSubmit = new Button("Submit"); btnSubmit.setFont(f); 

		add(lname);
		add(tfName);
		add(ladd);
		add(tfAdd);
		add(btnSubmit);

	}

}


