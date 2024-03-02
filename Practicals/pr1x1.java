// pgno 2 x1
import java.awt.*;
import java.applet.*;

/* <applet code="pr1x1" width="600" height="600"></applet> */
public class pr1x1 extends Applet 
{
Checkbox cbCo, cbMe, cbCi;
Checkbox cbMale ,cbFemale ;
CheckboxGroup gender;
Font f;


	public void init()
	{
		setBackground(Color.cyan);
		f = new Font("Arial",Font.BOLD,16);

		cbCo = new Checkbox("Computer"); cbCo.setFont(f); 
		cbMe = new Checkbox("Mechanical"); cbMe.setFont(f);
		cbCi = new Checkbox("Civil"); cbCi.setFont(f);

		gender = new CheckboxGroup();

		cbMale = new Checkbox("Male",gender , true); cbMale.setFont(f);
		cbFemale = new Checkbox("Female",gender,false); cbFemale.setFont(f);

		add(cbCo);
		add(cbMe);
		add(cbCi);

		add(cbMale);
		add(cbFemale);

	}

}


