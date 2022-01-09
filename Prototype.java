import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.lang.Math;
import javax.swing.table.DefaultTableModel;
import java.text.*; 
import java.time.*;
import java.time.format.*;
import java.text.DecimalFormat;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.Timer;
class Prototype implements Runnable
{
    //Main Frame
    JFrame frame1 = new JFrame("Billing & Payment");
    JTextField tf1,tf2,tf3,tf4;
    JTextArea ta1;
    JButton b1,b2,b3,b4;
    JLabel name,dob,cid,address,pn,email,title,calculate,clock1;
    JComboBox cb1,cb2,cb3;
    JRadioButton rb1,rb2,rb3,rb4;
    JMenu file,tool,help,calculator;
    JMenuItem nw,clear,exit,bill,bill2,bill3,emi,si,ci,gst,info,about;
    JMenuBar mb = new JMenuBar();
    Thread t=null;  
    int hours=0, minutes=0, seconds=0;  
    String timeString = "";  
    JLabel time;
    //Introduction Frame
    JFrame intro = new JFrame("Welcome");
    //consumer calculator frame
    JFrame frame2=new JFrame("EB Bill Calculator");
    JFrame frame3=new JFrame("Gas Bill Calculator");
    JFrame frame4=new JFrame("Water Bill Calculator");
    //finance calculator frame
    JFrame frame5=new JFrame("EMI calculator");
    JFrame frame6=new JFrame("SI Calculator");
    JFrame frame7=new JFrame("CI Calculator");
    JFrame frame9 =new JFrame("GST Calculator");
    JLabel gre,ititle,ver,inst; 
    Prototype()
    {
        file = new JMenu(" File");
        tool = new JMenu(" Tool ");
        help = new JMenu(" Help ");
        nw = new JMenuItem("New");
        clear = new JMenuItem("Clear");
        exit = new JMenuItem("Exit");
        calculator = new JMenu("Other Calculators");
        bill = new JMenuItem("EB Bill Calculator");
        bill2 = new JMenuItem("Gas Bill Calculator");
        bill3 = new JMenuItem("Water Bill Calculator");
        emi = new JMenuItem("EMI ");
        si = new JMenuItem("SI ");
        ci = new JMenuItem("CI ");
        gst = new JMenuItem("GST ");
        info = new JMenuItem("Info");
        about = new JMenuItem("About");
        file.add(nw); file.add(clear); file.add(exit);
        tool.add(bill);  tool.add(bill2);  tool.add(bill3); 
        tool.add(calculator); calculator.add(emi);  calculator.add(si);  calculator.add(ci); calculator.add(gst);
        help.add(info); help.add(about);
        mb.add(file); mb.add(tool); mb.add(help);
        t = new Thread(this);  
        t.start();  
        time=new JLabel();  
        time.setBounds(370,0,200,70);
        time.setFont(new Font("Verdana",Font.PLAIN,20)); //Arial Rounded MT Bold
        frame1.add(time);
    //Intro operations
    gre = new JLabel("Welcome to KAART");
    gre.setBounds(245,200,650,50);
    gre.setForeground(Color.red);
    gre.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 34));
    ititle = new JLabel("Billing & Payment Application");
    ititle.setBounds(250,20,370,30);
    ititle.setHorizontalAlignment(JLabel.CENTER);
	ititle.setFont(new Font("Times New Roman", Font.BOLD, 26));
    inst = new JLabel("Click any button(except power) to continue.");
    inst.setBounds(310,400,300,30);
    ver = new JLabel("Version 123012.032.043.01");
    ver.setBounds(360,500,200,32);
    JProgressBar jb;
        jb=new JProgressBar(0,18);  
        jb.setBounds(550,320,250,35);       
        jb.setStringPainted(true);  
        
    intro.add(gre); intro.add(ititle); intro.add(inst); intro.add(ver); 
    intro.setLayout(null);
    intro.setSize(900,600);
    intro.setVisible(true);
    intro.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);//frame1
    intro.setDefaultCloseOperation(intro.EXIT_ON_CLOSE);
    intro.addKeyListener(new KeyListener() 
    {
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyPressed(KeyEvent e) {intro.setVisible(false);frame1.setVisible(true);intro.dispose();}
        @Override 
        public void keyReleased(KeyEvent e) {}
    });
    intro.addMouseListener(new MouseListener() 
    {
        public void mouseClicked(MouseEvent e) {}  
        public void mouseEntered(MouseEvent e) {}  
        public void mouseExited(MouseEvent e) {}  
        public void mousePressed(MouseEvent e) {intro.setVisible(false);frame1.setVisible(true);intro.dispose(); }  
        public void mouseReleased(MouseEvent e) { }  
    });
        //Frame1 operations
        nw.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                tf1.setText(""); tf2.setText(""); tf3.setText(""); tf4.setText("");  ta1.setText(""); 
                cb1.setSelectedIndex(0); cb2.setSelectedIndex(0); cb3.setSelectedIndex(0);
                rb1.setSelected(false); rb2.setSelected(false); rb3.setSelected(false); 
                ButtonGroup bg1 = new ButtonGroup();
                bg1.add(rb1); bg1.add(rb2); bg1.add(rb3); bg1.clearSelection(); 
            }
        });
		clear.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                tf1.setText(""); tf2.setText(""); tf3.setText(""); tf4.setText("");  ta1.setText(""); 
                cb1.setSelectedIndex(0); cb2.setSelectedIndex(0); cb3.setSelectedIndex(0);
                rb1.setSelected(false); rb2.setSelected(false); rb3.setSelected(false); 
                ButtonGroup bg1 = new ButtonGroup();
                bg1.add(rb1); bg1.add(rb2); bg1.add(rb3); bg1.clearSelection();
                jb.setValue(0);
            }
        });
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
            { 
                Payment pp = new  Payment();
                frame1.dispose();intro.dispose();frame2.dispose();frame3.dispose();frame4.dispose();frame5.dispose();frame6.dispose();frame7.dispose();frame9.dispose();
                pp.exitb();
            }
		});
		bill.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e){
            if(tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty() || tf4.getText().isEmpty() || ta1.getText().isEmpty() || (cb1.getSelectedIndex()==0) ||cb2.getSelectedIndex()==0 ||cb3.getSelectedIndex()==0){
				JOptionPane.showMessageDialog(frame1," Input fields are empty,Payment not supported!","Alert",JOptionPane.WARNING_MESSAGE);}
            Ebframe();
			
		}
		});
		bill2.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e){
            if(tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty() || tf4.getText().isEmpty() || ta1.getText().isEmpty() || (cb1.getSelectedIndex()==0) ||cb2.getSelectedIndex()==0 ||cb3.getSelectedIndex()==0){
				JOptionPane.showMessageDialog(frame1,"Input fields are empty,Payment not supported! ","Alert",JOptionPane.WARNING_MESSAGE);}
			Gasframe();
		}
		});
        bill3.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e){
            if(tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty() || tf4.getText().isEmpty() || ta1.getText().isEmpty() || (cb1.getSelectedIndex()==0) ||cb2.getSelectedIndex()==0 ||cb3.getSelectedIndex()==0){
				JOptionPane.showMessageDialog(frame1,"Input fields are empty,Payment not supported! ","Alert",JOptionPane.WARNING_MESSAGE);}
			Waterframe();
		}
		});
		emi.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e){
			EMI();
		}
		});
        si.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e){
			SI();
		}
		});
        ci.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e){
			CI();
		}
		});
        gst.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e){
			GST();
		}
		});
        about.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			JFrame f=new JFrame();
            f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
            JDialog jd = new JDialog(f,"About");
			jd.setBounds(250, 150, 400, 300);
			JButton jButton = new JButton("Close");
			JTextArea dta = new JTextArea(10,10);
            dta.setFont(new Font("Times New Roman", Font.BOLD, 14));
            dta.setText(dta.getText()+"\n\t This application is made only for educational purposes. \t");
            dta.setText(dta.getText()+"\n ");
            dta.setText(dta.getText()+"\n                   Made by Raghul Raj A(123012032) and Thilageswaran K(123012043).\t"); 
            dta.setText(dta.getText()+"\n ");
            dta.setText(dta.getText()+"\n\t This application can't be applied for real(time/life) uses.");
            jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {jd.setVisible(false);}
			});
            dta.setEditable(false);
            jd.add(dta);
            jd.pack();
			jd.add(jButton,BorderLayout.SOUTH);
			jd.setVisible(true);}
		});
		info.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			JFrame f=new JFrame();
            f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
			JDialog jd = new JDialog(f,"Info");            
			jd.setBounds(250, 150, 400, 300);
			JButton jButton = new JButton("Close");
			JTextArea dta = new JTextArea(10,10);
            dta.setFont(new Font("Times New Roman", Font.BOLD, 14));
            dta.setText(dta.getText()+"\nStandard formulae used for Calculation.\nEB Bill Formula adapted from TN EB Tariff.\nGas and Water Billing Formula adapted form Indian Provider's billing method.  ");
			dta.setText(dta.getText()+"\n ");
            dta.setText(dta.getText()+"\n Note : In banking,account is credited with Rs10000 for demo purposes \n            by default.");
            jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {jd.setVisible(false);}
			});
			dta.setEditable(false);
            jd.add(dta);
            jd.pack();
			jd.add(jButton,BorderLayout.SOUTH);
			jd.setVisible(true);}
		});
        name = new JLabel("Name :");
        name.setBounds(20,70,250,20);
        tf1 = new JTextField();
        tf1.setBounds(110,70,245,30);
        cid = new JLabel("Customer ID :");
        cid.setBounds(20,120,250,20);
        tf2 = new JTextField();
        tf2.setBounds(110,120,245,30);
        dob = new JLabel("Date of Birth :");
        dob.setBounds(20,170,250,20);
        String date[] = {"DD","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String month[] = {"MM","01","02","03","04","05","06","07","08","09","10","11","12"};
        String year[] = {"YYYY","1998","1999","2000","2001","2002","2003","2004","2005"};
        cb1 = new JComboBox(date);
        cb1.setBounds(110,170,65,20);
        cb2 = new JComboBox(month);
        cb2.setBounds(198,170,65,20);
        cb3 = new JComboBox(year);
        cb3.setBounds(284,170,70,20);
        address = new JLabel("Address :");
        address.setBounds(20,220,250,20);
        ta1 = new JTextArea();
        ta1.setBounds(110,220,245,80);
        pn = new JLabel("Ph Number :");
        pn.setBounds(20,300,250,80);
        tf3 = new JTextField();
        tf3.setBounds(110,330,245,30);
        email = new JLabel("E-Mail ID");
        email.setBounds(20,380,250,20);
        tf4 = new JTextField();
        tf4.setBounds(110,380,245,30);
        b1 = new JButton("Save & Next");
        b1.setBounds(260,450,150,30);
        b2 = new JButton("Clear");
        b2.setBounds(470,450,150,30);
        b3 = new JButton("Next");
        b3.setBounds(380,450,100,30);
        calculate = new JLabel("Calculate");
        calculate.setBounds(550,70,250,20);
        calculate.setFont(new Font("Cascadia Code SemiBold",Font.BOLD,16));
        rb1 = new JRadioButton("EB Bill");
        rb1.setBounds(550,120,200,20);
        rb2 = new JRadioButton("Gas Bill");
        rb2.setBounds(550,170,200,20);
        rb3 = new  JRadioButton("Water Bill");
        rb3.setBounds(550,220,250,20);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1); bg.add(rb2); bg.add(rb3); bg.add(rb4);
        frame1.add(mb); frame1.setJMenuBar(mb);
        frame1.add(name); frame1.add(tf1);
        frame1.add(cid); frame1.add(tf2);
        frame1.add(dob);   frame1.add(cb1); frame1.add(cb2); frame1.add(cb3);
        frame1.add(address); frame1.add(ta1);
        frame1.add(pn); frame1.add(tf3);
        frame1.add(email); frame1.add(tf4);
        frame1.add(b1); frame1.add(b2);
        frame1.add(calculate); 
        frame1.add(rb1); frame1.add(rb2); frame1.add(rb3); frame1.add(jb);
        tf1.getDocument().addDocumentListener(new DocumentListener() {
            int value = jb.getValue();  
            @Override public void insertUpdate(DocumentEvent e){jb.setValue(value+3);}
            @Override public void changedUpdate(DocumentEvent e) { }
            @Override public void removeUpdate(DocumentEvent e) {jb.setValue(value-3);} 
        });
        tf2.getDocument().addDocumentListener(new DocumentListener() {
            int value = jb.getValue()+3;  
            @Override public void insertUpdate(DocumentEvent e){jb.setValue(value+3); }
            @Override public void changedUpdate(DocumentEvent e) { }
            @Override public void removeUpdate(DocumentEvent e) {jb.setValue(value-3); }    
        });
        cb1.addItemListener(new ItemListener() {
            int value = jb.getValue()+6;  
            public void itemStateChanged(ItemEvent ae) 
            {if (ae.getStateChange() == ItemEvent.SELECTED) 
                {int ck = cb1.getSelectedIndex();
                    if(ck==0){jb.setValue(value-1);}
                    else{jb.setValue(value+1);}
                }
            }
        });
        cb2.addItemListener(new ItemListener() {
            int value = jb.getValue()+7;  
            public void itemStateChanged(ItemEvent ae) 
            {if (ae.getStateChange() == ItemEvent.SELECTED) 
                {int ck = cb2.getSelectedIndex();
                    if(ck==0){jb.setValue(value-1);}
                    else{jb.setValue(value+1);}
                }
            }
        });
        cb3.addItemListener(new ItemListener() {
            int value = jb.getValue()+8;  
            public void itemStateChanged(ItemEvent ae) 
            {if (ae.getStateChange() == ItemEvent.SELECTED) 
                {int ck = cb3.getSelectedIndex();
                    if(ck==0){jb.setValue(value-1);}
                    else{jb.setValue(value+1);}
                }
            }
        });
        ta1.getDocument().addDocumentListener(new DocumentListener() {
            int value = jb.getValue()+9;  
            @Override public void insertUpdate(DocumentEvent e){jb.setValue(value+3);}
            @Override public void changedUpdate(DocumentEvent e) { }
            @Override public void removeUpdate(DocumentEvent e) { jb.setValue(value-3); }    
        });
        tf3.getDocument().addDocumentListener(new DocumentListener() {
            int value = jb.getValue()+12;  
            @Override public void insertUpdate(DocumentEvent e){jb.setValue(value+3);}
            @Override public void changedUpdate(DocumentEvent e) { }
            @Override public void removeUpdate(DocumentEvent e) {jb.setValue(value-3); }    
        });
        tf4.getDocument().addDocumentListener(new DocumentListener() {
            int value = jb.getValue()+15;  
            @Override public void insertUpdate(DocumentEvent e){jb.setValue(value+3);}
            @Override public void changedUpdate(DocumentEvent e) { }
            @Override public void removeUpdate(DocumentEvent e) { jb.setValue(value-3); }    
        });
        jb.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) 
            {
                if(jb.getValue()!=18){jb.setString("Input Status");}
                else{jb.setString("Proceed");}   
            }
        });
        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty() || tf4.getText().isEmpty() || ta1.getText().isEmpty() || (cb1.getSelectedIndex()==0) ||cb2.getSelectedIndex()==0 ||cb3.getSelectedIndex()==0){
				JOptionPane.showMessageDialog(frame1,"Input fields are empty!","Alert",JOptionPane.WARNING_MESSAGE);}
                else{
                JOptionPane.showMessageDialog(frame1,"Saved Successfully.");
                if(rb1.isSelected())
                {
                   Ebframe();
                }
                if(rb2.isSelected())
                {
                    Gasframe();
                }
                if(rb3.isSelected())
                {
                    Waterframe();
                } }
            }
        });
        b2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               if(JOptionPane.showConfirmDialog(frame1,"Confirm to Clear.","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
               tf1.setText(""); tf2.setText(""); tf3.setText(""); tf4.setText(""); ta1.setText("");
               cb1.setSelectedIndex(0); cb2.setSelectedIndex(0); cb3.setSelectedIndex(0); 
               bg.clearSelection();  jb.setValue(0); 
            }
        });
        frame1.setLayout(null);
        frame1.setSize(900,600);
        //frame1.setVisible(true);
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        frame1.setDefaultCloseOperation(intro.EXIT_ON_CLOSE);  
    }
public void Ebframe()
    {
		//JFrame frame2=new JFrame("EB Bill Calculator");
	    JLabel title,st,wa,id,tar,amt;
	    JTextField wa1,id1;
		JTextArea amt1;
	    JComboBox c1;
	    JRadioButton r1,r2;
	    title=new JLabel("EB Bill Calculator");
	    title.setHorizontalAlignment(JLabel.CENTER);
	    title.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	    title.setBounds(250,0,200,100);
	    st= new JLabel("State:");
	    st.setBounds(20,100,200,25);
        String state[]={"----Select----","Tamil Nadu"};
	    c1= new JComboBox(state);
	    c1.setBounds(200,100,150,25);
	    id=new JLabel("EB consumer ID:");
	    id.setBounds(20,150,150,25);
	    id1=new JTextField();
	    id1.setBounds(200,150,150,25);
	    wa=new JLabel("Units consumed:");
	    wa.setBounds(20,200,150,25);
	    wa1=new JTextField();
        wa1.setBounds(200,200,150,25);
        tar=new JLabel("Tariff select:");
        tar.setBounds(20,250,150,25);
        r1=new JRadioButton("Domestic");r2=new JRadioButton("Industrial");
        ButtonGroup g1=new ButtonGroup();
        g1.add(r1);g1.add(r2);
        r1.setBounds(200,250,150,25);
        r2.setBounds(200,275,150,25);
        amt =new JLabel("Charges:");
        amt.setBounds(20,325,150,25);
        amt1=new JTextArea();
        amt1.setBounds(200,325,150,25);	
        JButton cal=new JButton("Calculate");
        cal.setBounds(170,375,100,25);
        JButton pay=new JButton("Pay");
        pay.setBounds(290,375,100,25);
        JButton exit=new JButton("Exit");
        exit.setBounds(410,375,100,25);
        frame2.add(title);
        frame2.add(st);frame2.add(c1);
        frame2.add(id);frame2.add(id1);
        frame2.add(wa);frame2.add(wa1);
        frame2.add(tar);frame2.add(r1);frame2.add(r2);
        frame2.add(amt);frame2.add(amt1);
        frame2.add(cal);frame2.add(pay);frame2.add(exit);
		if(tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty() || tf4.getText().isEmpty() || ta1.getText().isEmpty() || (cb1.getSelectedIndex()==0) ||cb2.getSelectedIndex()==0 ||cb3.getSelectedIndex()==0){frame2.remove(pay);}
        else {frame2.add(pay);};
        frame2.setLayout(null);
        frame2.setSize(700,500);
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(frame2.EXIT_ON_CLOSE);
		cal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
            try{
			long units=Long.parseLong(wa1.getText());
			if(r1.isSelected()){amt1.setText(String.valueOf(Household(units)));}
			if(r2.isSelected()){amt1.setText(String.valueOf(Industrial(units)));}
            }
                catch(NumberFormatException nfe){JOptionPane.showMessageDialog(frame2,"Input(Invalid/Empty)","Alert",JOptionPane.WARNING_MESSAGE);}
                catch(InputMismatchException ime){JOptionPane.showMessageDialog(frame2,"Input(Invalid/Empty)","Alert",JOptionPane.WARNING_MESSAGE);}
			}
		});
        pay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){ 
			Payment pp=new Payment();
			String name = tf1.getText();
			String id = tf2.getText();
			String s1=cb1.getSelectedItem().toString();
            String s2=cb2.getSelectedItem().toString();
			String s3=cb3.getSelectedItem().toString();
			String Dob=s1.concat(s2).concat(s3);
            //int dob =  Integer.parseInt(Dob);
			String address=ta1.getText();
			String phone=tf3.getText();
            //int phone =  Integer.parseInt(Phone);
			String  mail=tf4.getText();
			String I_D=id1.getText();
            //int I_D = Integer.parseInt(id1.getText());
			Double charges = Double.parseDouble(amt1.getText());
            DecimalFormat df = new DecimalFormat("#.##");
            charges = Double.valueOf(df.format(charges));
            //String getname,String getid,int DOB,String address,String Phno,String mail,String to,String id,double amttp
			pp.pay(name,id,Dob,address,phone,mail,"TN Gov",I_D,charges);}
		});
        exit.addActionListener(new ActionListener()
        {
        public void actionPerformed(ActionEvent e)
        {frame2.dispose();}
        });
	}
	double Household(long units)
	{
	  double billpay;
	  int fixed=30;
  	  if(units>0 && units<=100)
		 return billpay=units*0+fixed;
	  else if(units>100 && units<=200)
		 return billpay = 100*0 + (units-100)*1.5 + fixed;
	  else if(units>200 && units<=500)
		 return billpay = 100*0 + 100*2 + (units-200)*3 + fixed;
	  else if(units>500)
		 return billpay = 100*0 + 100 *3.5 + 300*4.6 + (units-500)*6.6 + fixed;
	  else 
		  return 0;
    }
    double Industrial(long units)
    {
        double billpay;
		return billpay=units*6.35;
    }	
void Gasframe()
    {
        //JFrame frame3=new JFrame("Gas Bill Calculator");
    	JLabel title,pro,pr,cr,id,rate,amt,total;
        JTextField read1,read2,id1;
        JTextArea amt1,tc;
    	JComboBox c1;
    	JRadioButton r1,r2,r3;
    	title=new JLabel("Gas Bill Calculator");
    	title.setHorizontalAlignment(JLabel.CENTER);
    	title.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	    title.setBounds(250,0,200,100);
	    pro= new JLabel("Provider : ");
    	pro.setBounds(20,100,200,25);
        String provider[]={"----Select----","TN gas corporation"};
        c1= new JComboBox(provider);
        c1.setBounds(200,100,150,25);
        id=new JLabel("Gas consumer ID :");
        id.setBounds(20,150,150,25);
        id1=new JTextField();
        id1.setBounds(200,150,150,25);
        pr=new JLabel("Previous Meter Reading :");
        pr.setBounds(20,200,150,25);
        read1=new JTextField();
        read1.setBounds(200,200,150,25);
        cr = new JLabel("Current Meter Reading :");
        cr.setBounds(20,250,150,25);
        read2=new JTextField();
        read2.setBounds(200,250,150,25);
        rate=new JLabel("Select Type :");
        rate.setBounds(20,300,150,25);
        r1=new JRadioButton("Slab I");r2=new JRadioButton("Slab II");r3=new JRadioButton("Slab III");
        ButtonGroup g1=new ButtonGroup();
        g1.add(r1);g1.add(r2);g1.add(r3);
        r1.setBounds(200,300,80,25);
        r2.setBounds(280,300,80,25);
        r3.setBounds(360,300,80,25);
        amt =new JLabel("Total Charges :");
        amt.setBounds(20,350,150,25);
        amt1=new JTextArea();
        amt1.setBounds(200,350,150,25);	
        total = new JLabel("Total Consumption :");
        total.setBounds(390,350,150,25);
        tc = new JTextArea();
        tc.setBounds(530,350,75,25);
        JButton cal=new JButton("Calculate");
        cal.setBounds(170,400,100,25);
        JButton pay=new JButton("Pay");
        pay.setBounds(290,400,100,25);
        JButton exit=new JButton("Exit");
        exit.setBounds(410,400,100,25);
        frame3.add(title);
        frame3.add(pro);frame3.add(c1);
        frame3.add(id);frame3.add(id1);
        frame3.add(pr); frame3.add(cr);
        frame3.add(read1);frame3.add(read2);
        frame3.add(rate);frame3.add(r1);frame3.add(r2); frame3.add(r3);
        frame3.add(amt);frame3.add(amt1);
        frame3.add(total);frame3.add(tc);
        frame3.add(cal);frame3.add(pay);frame3.add(exit);
        if(tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty() || tf4.getText().isEmpty() || ta1.getText().isEmpty() || (cb1.getSelectedIndex()==0) ||cb2.getSelectedIndex()==0 ||cb3.getSelectedIndex()==0){frame3.remove(pay);}
	    else {frame3.add(pay);}
        frame3.setLayout(null);
        frame3.setSize(700,500);
        frame3.setVisible(true);
        frame3.setDefaultCloseOperation(frame3.EXIT_ON_CLOSE);
        cal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
            {
            try{
			double pre =Double.parseDouble(read1.getText());
            double cur =Double.parseDouble(read2.getText());
            double tot = cur-pre;
            tc.setText(String.valueOf(tot));
			if(r1.isSelected()){amt1.setText(String.valueOf(sl(pre,cur)));}
			if(r2.isSelected()){amt1.setText(String.valueOf(sla(pre,cur)));}
            if(r3.isSelected()){amt1.setText(String.valueOf(slab(pre,cur)));}
            }
            catch(NumberFormatException nfe){JOptionPane.showMessageDialog(frame3,"Input(Invalid/Empty)","Alert",JOptionPane.WARNING_MESSAGE);}
            catch(InputMismatchException ime){JOptionPane.showMessageDialog(frame3,"Input(Invalid/Empty)","Alert",JOptionPane.WARNING_MESSAGE);}
			}
		});
        pay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){ 
			Payment pp=new Payment();
            String name = tf1.getText();
			String id=tf2.getText();
			String s1=cb1.getSelectedItem().toString();
            String s2=cb2.getSelectedItem().toString();
			String s3=cb3.getSelectedItem().toString();
			String Dob=s1.concat(s2).concat(s3);
            //int dob =  Integer.parseInt(Dob);
			String address=ta1.getText();
			String phone=tf3.getText();
            //int phone =  Integer.parseInt(Phone);
			String  mail=tf4.getText();
			String I_D=id1.getText();
            //int I_D = Integer.parseInt(id1.getText());
			Double charges=Double.parseDouble(amt1.getText());
            DecimalFormat df = new DecimalFormat("#.##");
            charges = Double.valueOf(df.format(charges));
            //String getname,String getid,int DOB,String address,String Phno,String mail,String to,String id,double amttp
			pp.pay(name,id,Dob,address,phone,mail,"TN Gov",I_D,charges);}
		});
        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {frame3.dispose();}
        });
    }
    double sl(double pre,double cur)
        {
            double tot = cur - pre;
            return tot*21.96;
        }
    double sla(double pre,double cur)
        {
            double tot = cur - pre;
            return tot*26.01;
        }
    double slab(double pre,double cur)
        {
            double tot = cur - pre;
            return tot*33.36;
        }
 void Waterframe()
    {
		//JFrame frame4=new JFrame("Water Bill Calculator");
		JLabel t,pr,w_id,uni,days,cha;
		JTextField id1,uni1,days1,cha1;
		JComboBox com1;
		t=new JLabel("Water Bill Calculator");
		t.setHorizontalAlignment(JLabel.CENTER);
		t.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		t.setBounds(250,0,200,100);
		pr= new JLabel("Provider : ");
		pr.setBounds(20,100,200,25);
		String providers[]={"----Select----","City corporation"};
		com1= new JComboBox(providers);
		com1.setBounds(200,100,150,25);
		w_id=new JLabel("Consumer ID :");
		w_id.setBounds(20,150,150,25);
		id1=new JTextField();
		id1.setBounds(200,150,150,25);
		uni=new JLabel("Units consumed :");
		uni.setBounds(20,200,150,25);
		uni1=new JTextField();
		uni1.setBounds(200,200,150,25);
		days=new JLabel("No of days :");
		days.setBounds(20,250,150,25);
		days1=new JTextField();
		days1.setBounds(200,250,150,25);
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable();
        Object[] columns = {"Consumption","Sewer Charge","Service Charge"};
        tableModel.setColumnIdentifiers(columns);
        table.setModel(tableModel);
        table.setBounds(150,300,300,50);
        Object[] row = new Object[3];
        JScrollPane scroll = new JScrollPane (table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(150,300,375,80);
		cha=new JLabel("Total Charges :");
		cha.setBounds(20,395,150,25);
		cha1=new JTextField();
		cha1.setBounds(200,395,150,25);
		JButton cal=new JButton("Calculate");
        cal.setBounds(170,450,100,25);
        JButton pay=new JButton("Pay");
        pay.setBounds(290,450,100,25);
        JButton exit=new JButton("Exit");
        exit.setBounds(410,450,100,25);
		frame4.add(t);frame4.add(pr);frame4.add(com1);frame4.add(w_id);frame4.add(id1);frame4.add(uni);frame4.add(uni1);frame4.add(days);frame4.add(days1);
		frame4.add(cal);frame4.add(pay);frame4.add(exit);frame4.add(scroll);frame4.add(cha);frame4.add(cha1);
		if(tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty() || tf4.getText().isEmpty() || ta1.getText().isEmpty() || (cb1.getSelectedIndex()==0) ||cb2.getSelectedIndex()==0 ||cb3.getSelectedIndex()==0){frame4.remove(pay);}
		else {frame4.add(pay);}
        frame4.setLayout(null);
		frame4.setSize(700,600);
		frame4.setDefaultCloseOperation(frame4.EXIT_ON_CLOSE);
		frame4.setVisible(true);
        cal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
            {
            try{
			double u = Double.parseDouble(uni1.getText());
            double nod =Double.parseDouble(days1.getText());
            double CC = u*nod*12.3;
            double SWC = u*nod*7.38;
            double SEC = u*nod*7.33;
            row[0] = CC; row[1] = SWC; row[2] = SEC;
            tableModel.addRow(row);
            cha1.setText(String.valueOf(wb(u,nod)));
            }
                catch(NumberFormatException nfe){JOptionPane.showMessageDialog(frame4,"Input(Invalid/Empty)","Alert",JOptionPane.WARNING_MESSAGE);}
                catch(InputMismatchException ime){JOptionPane.showMessageDialog(frame4,"Input(Invalid/Empty)","Alert",JOptionPane.WARNING_MESSAGE);}
			}
		});
        pay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){ 
			Payment pp=new Payment();
            String name = tf1.getText();
			String id=tf2.getText();
			String s1=cb1.getSelectedItem().toString();
            String s2=cb2.getSelectedItem().toString();
			String s3=cb3.getSelectedItem().toString();
			String Dob=s1.concat(s2).concat(s3);
            //int dob =  Integer.parseInt(Dob);
			String address=ta1.getText();
			String phone=tf3.getText();
            //int phone =  Integer.parseInt(Phone);
			String  mail=tf4.getText();
			String I_D=id1.getText();
            //int I_D = Integer.parseInt(id1.getText());
			Double charges=Double.parseDouble(cha1.getText());
            DecimalFormat df = new DecimalFormat("#.##");
            charges = Double.valueOf(df.format(charges));
            //String getname,String getid,int DOB,String address,String Phno,String mail,String to,String id,double amttp
			pp.pay(name,id,Dob,address,phone,mail,"TN Gov",I_D,charges);}
		});
        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {frame4.dispose();}
        });
    }
	double wb(double u,double nod)
    {
        double totw = u*nod*(12.3+7.38+7.33);
        return totw;
    }
public  void EMI()
    {
		//JFrame frame5=new JFrame("EMI calculator");
		JLabel title2,amt2,interest,months,Emi;
		JTextField amt3,interest1,mon,Emi1;
		title2=new JLabel("EMI Calculator");
	    title2.setHorizontalAlignment(JLabel.CENTER);
	    title2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	    title2.setBounds(250,0,200,100);
	    amt2= new JLabel("Enter total amount:");
	    amt2.setBounds(20,100,200,25);
		amt3=new JTextField();
	    amt3.setBounds(200,100,150,25);
	    interest=new JLabel("Rate of interest:(in %)");
	    interest.setBounds(20,150,150,25);
	    interest1=new JTextField();
	    interest1.setBounds(200,150,150,25);
	    months=new JLabel("No of Months:");
	    months.setBounds(20,200,150,25);
	    mon=new JTextField();
        mon.setBounds(200,200,150,25);
		Emi=new JLabel("EMI (/month):");
	    Emi.setBounds(20,250,150,25);
	    Emi1=new JTextField();
        Emi1.setBounds(200,250,150,25);
		JButton cal1=new JButton("Calculate");
        cal1.setBounds(170,375,100,25);
        JButton exit1=new JButton("Exit");
        exit1.setBounds(410,375,100,25);
		JButton clr=new JButton("Clear");
        clr.setBounds(290,375,100,25);
        JButton exit=new JButton("Exit");
		frame5.add(title2);frame5.add(amt2);frame5.add(amt3);frame5.add(interest);frame5.add(interest1);
		frame5.add(months);frame5.add(mon);frame5.add(Emi);frame5.add(Emi1);frame5.add(cal1);frame5.add(exit1);frame5.add(clr);
		frame5.setLayout(null);
        frame5.setSize(700,500);
        frame5.setVisible(true);
        frame5.setDefaultCloseOperation(frame5.EXIT_ON_CLOSE);
		cal1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
                try{
				double i=Double.parseDouble(amt3.getText());
				double j=Double.parseDouble(interest1.getText());
				int k=Integer.parseInt(mon.getText());
				j=j/100;
				double emi= (i * j * Math.pow(1+j,k)) / (Math.pow((1+j),k)-1);
                DecimalFormat df = new DecimalFormat("#.##");
                emi = Double.valueOf(df.format(emi));
				Emi1.setText(String.valueOf(emi));
                }
                catch(NumberFormatException nfe){JOptionPane.showMessageDialog(frame5,"Input(Invalid/Empty)","Alert",JOptionPane.WARNING_MESSAGE);}
                catch(InputMismatchException ime){JOptionPane.showMessageDialog(frame5,"Input(Invalid/Empty)","Alert",JOptionPane.WARNING_MESSAGE);}
		}
		});
		clr.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
			   if(JOptionPane.showConfirmDialog(frame5,"Confirm to Clear.","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
               amt3.setText(""); interest1.setText(""); mon.setText(""); Emi1.setText(""); 
			}
		});
		exit1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){ frame5.dispose();}
		});
    } 
public void SI()
    {
		//JFrame frame6=new JFrame("SI Calculator");
		JLabel title2,amt2,rate,punit,popt,si;
        JComboBox punit2;
		JTextField amt3,rate1,period,si1;
		title2=new JLabel("Simple Interest Calculator");
	    title2.setHorizontalAlignment(JLabel.CENTER);
	    title2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	    title2.setBounds(210,0,250,100);
	    amt2= new JLabel("Enter total amount :");
	    amt2.setBounds(20,100,200,25);
		amt3=new JTextField();
	    amt3.setBounds(200,100,150,25);
	    rate=new JLabel("Rate of interest(in %) :");
	    rate.setBounds(20,150,150,25);
	    rate1=new JTextField();
	    rate1.setBounds(200,150,150,25);
	    punit=new JLabel("Period Unit :");
	    punit.setBounds(20,200,100,25);
        String option[] = {"--select--","Months","Years"};
        punit2 = new JComboBox(option);
        punit2.setBounds(100,203,100,20);
        popt = new JLabel("Period Optional : ");
        popt.setBounds(230,200,100,25);
        period = new JTextField();
        period.setBounds(350,200,100,25);
		si=new JLabel("Simple Interest(per year) :");
	    si.setBounds(20,250,150,25);
	    si1=new JTextField();
        si1.setBounds(200,250,150,25);
		JButton cal1=new JButton("Calculate");
        cal1.setBounds(170,375,100,25);
        JButton exit1=new JButton("Exit");
        exit1.setBounds(410,375,100,25);
        JButton clr2=new JButton("Clear");
        clr2.setBounds(290,375,100,25);
		frame6.add(title2);frame6.add(amt2);frame6.add(amt3);frame6.add(rate);frame6.add(rate1);
		frame6.add(punit);frame6.add(punit2);
        frame6.add(popt); frame6.add(period);
        frame6.add(si);frame6.add(si1);frame6.add(cal1);frame6.add(exit1); frame6.add(clr2);
		frame6.setLayout(null);
        frame6.setSize(700,500);
        frame6.setVisible(true);
        frame6.setDefaultCloseOperation(frame6.EXIT_ON_CLOSE);
		cal1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
            {
                try{
				double p = Double.parseDouble(amt3.getText());
				double r = Double.parseDouble(rate1.getText());
                double n = Double.parseDouble(period.getText());
				if(punit2.getSelectedIndex() == 1)
                    {
                        double si = ((p * n * r)/100)/n;
                        DecimalFormat df = new DecimalFormat("#.##");
                        si = Double.valueOf(df.format(si));
                        si1.setText(String.valueOf(si));
                    }
				if(punit2.getSelectedIndex() == 2)
                    {
                        double si = (p * n * r)/100;
                        DecimalFormat df = new DecimalFormat("#.##");
                        si = Double.valueOf(df.format(si));
                        si1.setText(String.valueOf(si));
                    }
                }
                catch(NumberFormatException nfe){JOptionPane.showMessageDialog(frame6,"Input(Invalid/Empty)","Alert",JOptionPane.WARNING_MESSAGE);}
                catch(InputMismatchException ime){JOptionPane.showMessageDialog(frame6,"Input(Invalid/Empty)","Alert",JOptionPane.WARNING_MESSAGE);}
			}
		});
        clr2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
			   if(JOptionPane.showConfirmDialog(frame6,"Confirm to Clear.","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
               amt3.setText(""); rate1.setText(""); period.setText(""); si1.setText("");
               punit2.setSelectedIndex(0); 
			}
		});
		exit1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){ frame6.dispose();}
		});	
	}
public void CI()
    {
		//JFrame frame7=new JFrame("CI Calculator");
		JLabel title3,amtc,rate,comp,popt,ci,time,dur;
        JComboBox comp1;
		JTextField amt3,rate1,period,ci1,time1,dur1;
		title3=new JLabel("Compound Interest Calculator");
	    title3.setHorizontalAlignment(JLabel.CENTER);
	    title3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	    title3.setBounds(210,0,250,100);
	    amtc= new JLabel("Enter total amount :");
	    amtc.setBounds(20,100,200,25);
		amt3=new JTextField();
	    amt3.setBounds(200,100,150,25);
	    rate=new JLabel("Rate of interest(in %) :");
	    rate.setBounds(20,150,150,25);
	    rate1=new JTextField();
	    rate1.setBounds(200,150,150,25);
	    comp=new JLabel("Compound :");
	    comp.setBounds(20,200,100,25);
        String option[] = {"--select--","Days","Monthly","Yearly"};
        comp1= new JComboBox(option);
        comp1.setBounds(200,200,100,20);
        dur= new JLabel("Duration :");
        dur.setBounds(20,250,150,25);
        dur1 = new JTextField();
        dur1.setBounds(200,250,100,25);
        time = new JLabel("Time (t in years) :");
        time.setBounds(20,300,100,25);
        time1 = new JTextField();
        time1.setBounds(200,300,100,25);
		ci=new JLabel("Compound Interest :");
	    ci.setBounds(20,350,150,25);
	    ci1=new JTextField();
        ci1.setBounds(200,350,150,25);
		JButton cal1=new JButton("Calculate");
        cal1.setBounds(170,405,100,25);
        JButton exit1=new JButton("Exit");
        exit1.setBounds(410,405,100,25);
        JButton clr3=new JButton("Clear");
        clr3.setBounds(290,405,100,25);
		frame7.add(title3);frame7.add(amtc);frame7.add(amt3);frame7.add(rate);frame7.add(rate1);
		frame7.add(comp);frame7.add(comp1);
        frame7.add(time); frame7.add(time1);
        frame7.add(dur); frame7.add(dur1);
        frame7.add(ci);frame7.add(ci1);frame7.add(cal1);frame7.add(exit1); frame7.add(clr3);
		frame7.setLayout(null);
        frame7.setSize(700,500);
        frame7.setVisible(true);
        frame7.setDefaultCloseOperation(frame7.EXIT_ON_CLOSE);
		cal1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
            {
                try{
                double pc = Double.parseDouble(amt3.getText());
				double rc = Double.parseDouble(rate1.getText());
                rc = rc/100;
                double nc = Double.parseDouble(time1.getText());
				if(comp1.getSelectedIndex() == 1)
                    {
                        if(dur1.getText().equals(""))
                        {   int du = 365;
                            double ci = pc * (Math.pow((1 + rc/du), (du * nc )));
                            DecimalFormat df = new DecimalFormat("#.##");
                            ci = Double.valueOf(df.format(ci));
                            ci1.setText(String.valueOf(ci));    }
                        else
                        {   double du = Double.parseDouble(dur1.getText());
                            double ci = pc * (Math.pow((1 + rc/du), (du * nc )));
                            DecimalFormat df = new DecimalFormat("#.##");
                            ci = Double.valueOf(df.format(ci));
                            ci1.setText(String.valueOf(ci));    }
                    }
				if(comp1.getSelectedIndex() == 2)
                    {
                        if(dur1.getText().equals(""))
                        {   int du = 12;
                            double ci = pc * (Math.pow((1 + rc/du), (du * nc )));
                            DecimalFormat df = new DecimalFormat("#.##");
                            ci = Double.valueOf(df.format(ci));
                            ci1.setText(String.valueOf(ci));    }
                        else
                        {   double du = Double.parseDouble(dur1.getText());
                            double ci = pc * (Math.pow((1 + rc/du), (du * nc )));
                            DecimalFormat df = new DecimalFormat("#.##");
                            ci = Double.valueOf(df.format(ci));
                            ci1.setText(String.valueOf(ci));    }
                    }
                if(comp1.getSelectedIndex() == 3)
                    {
                        if(dur1.getText().equals(""))
                        {   int du = 12;
                            double ci = pc * (Math.pow((1 + rc/du), (du * nc )));
                            DecimalFormat df = new DecimalFormat("#.##");
                            ci = Double.valueOf(df.format(ci));
                            ci1.setText(String.valueOf(ci));    }
                        else
                        {   double du = Double.parseDouble(dur1.getText());
                            double ci = pc * (Math.pow((1 + rc/du), (du * nc )));
                            DecimalFormat df = new DecimalFormat("#.##");
                            ci = Double.valueOf(df.format(ci));
                            ci1.setText(String.valueOf(ci));    }
                    }
                    }
                    catch(NumberFormatException nfe){JOptionPane.showMessageDialog(frame7,"Input(Invalid/Empty)","Alert",JOptionPane.WARNING_MESSAGE);}
                    catch(InputMismatchException ime){JOptionPane.showMessageDialog(frame7,"Input(Invalid/Empty)","Alert",JOptionPane.WARNING_MESSAGE);}
			}
		});
        clr3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
			   if(JOptionPane.showConfirmDialog(frame7,"Confirm to Clear.","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
               amt3.setText(""); rate1.setText(""); time1.setText(""); ci1.setText(""); dur1.setText("");
               comp1.setSelectedIndex(0); 
			}
		});
		exit1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){ frame7.dispose();}
		});	
	}
public void GST()
    {
        //JFrame frame9 =new JFrame("GST Calculator");
		JLabel title4,op,fp,sel;
		JTextField optf,fptf;
        title4 = new JLabel("GST Calculator");
        title4.setHorizontalAlignment(JLabel.CENTER);
	    title4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	    title4.setBounds(90,0,250,100);
        op = new JLabel("Original Price :");
        op.setBounds(20,90,200,25);
        optf = new JTextField();
        optf.setBounds(130,90,150,25);
        sel = new JLabel("GST :");
        sel.setBounds(20,140,250,25);
        DefaultListModel<String> l1 = new DefaultListModel<>();
        l1.addElement(" 3% ");
        l1.addElement(" 5% ");
        l1.addElement(" 12% ");
        l1.addElement(" 18% ");
        l1.addElement(" 28% ");
        JList<String> list = new JList<>(l1);
        list.setBounds(130,140,50,90);
        fp = new JLabel("Final Price :");
        fp.setBounds(20,260,200,25);
        fptf = new JTextField();
        fptf.setBounds(130,260,150,25);
        JButton cal2=new JButton("Calculate");
        cal2.setBounds(70,330,90,25);
        JButton exit2=new JButton("Exit");
        exit2.setBounds(170,330,90,25);
        JButton clr3=new JButton("Clear");
        clr3.setBounds(270,330,90,25);
        frame9.add(title4); frame9.add(op); frame9.add(optf);
        frame9.add(sel); frame9.add(list); 
        frame9.add(fp); frame9.add(fptf);
        frame9.add(cal2); frame9.add(exit2); frame9.add(clr3);
        frame9.setLayout(null);
        frame9.setSize(450,450);
        frame9.setVisible(true);
        frame9.setDefaultCloseOperation(frame9.EXIT_ON_CLOSE);
        cal2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
            {
                try{
				double OP = Double.parseDouble(optf.getText());
                int n = list.getSelectedIndex();
                int perc = 0;
                if(n==0) perc = 3; if(n==1) perc = 5; if(n==2) perc = 12;
                if(n==3) perc = 18; if(n==4) perc = 28; 
                double FP = OP + ((OP * perc)/100);
                DecimalFormat df = new DecimalFormat("#.##");
                FP = Double.valueOf(df.format(FP));
                fptf.setText(String.valueOf(FP));
                }
                catch(NumberFormatException nfe){JOptionPane.showMessageDialog(frame9,"Input(Invalid/Empty)","Alert",JOptionPane.WARNING_MESSAGE);}
                catch(InputMismatchException ime){JOptionPane.showMessageDialog(frame9,"Input(Invalid/Empty)","Alert",JOptionPane.WARNING_MESSAGE);}
			}
		});
        clr3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
			   if(JOptionPane.showConfirmDialog(frame9,"Confirm to Clear.","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
               optf.setText(""); fptf.setText(""); list.clearSelection();
			}
		});
		exit2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){ frame9.dispose();}
		});	
    }
    public void run() 
        {  
            try {  
                while (true) 
                    {  
                        Calendar cal = Calendar.getInstance();  
                        hours = cal.get( Calendar.HOUR_OF_DAY );  
                        if ( hours > 12 ) hours -= 12;  
                        minutes = cal.get( Calendar.MINUTE );  
                        seconds = cal.get( Calendar.SECOND );  
                        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");  
                        Date date = cal.getTime();  
                        timeString = formatter.format( date );  
                        printTime();  
                        t.sleep( 1000 ); 
                    }  
                }         
            catch (Exception e) { }  
        }  
        public void printTime()
        {  
        time.setText(timeString);  
        }  
    public static void main(String[] args)
    {
        new Prototype();
    }
}
//payment operation
class Payment
{
    JFrame Connecting = new JFrame("Connecting to server");
    JFrame frame8 = new JFrame("Bank");
    JFrame login = new JFrame("Login");
    public void pay(String getname,String getid,String DOB,String address,String Phno,String mail,String to,String id,double amttp)
    {
    String name = getname;
    String Name = name.substring(0, 1).toUpperCase() + name.substring(1);
    String cusid = getid;
    String daob = DOB;
    String rrpin = daob.substring(0,4);
    String date = daob.substring(0, 2);
    String month = daob.substring(2, 4);
    String year = daob.substring(4, 8);
    String PIN2 = rrpin;//Integer.parseInt(rrpin);
    String addr = address;
    String pno = Phno;
    String email = mail;
    String TO = to;
    String ID = id;
    double amt = amttp;
    BankAccountprototype bap = new BankAccountprototype();
    //JFrame frame8 = new JFrame("Bank");
    //JFrame login = new JFrame("Login");
    JLabel greet,al,uname,bal,act,pw,un,det,help;
    JTextField tf1,tf2;
    JTextArea ta1,ta2;
    JButton b1,b2,b3,b4,b5,b6,b7;
//Connecting frame 
    //JFrame Connecting = new JFrame("Connecting to server");
    JProgressBar jb = new JProgressBar(0,100);  
    jb.setBounds(255,230,250,30);       
    jb.setValue(0);  
    jb.setStringPainted(true);  
    jb.setString("Connecting to Bank Server");
    //jb.setVisible(true);
    //while(i<=100){jb.setValue(i);  i=i+10;  try{Thread.sleep(200);}catch(Exception e){}  if(jb.getValue() == 100){  login.setVisible(true);Connecting.dispose();/*operation*/ } }  
    final Timer timer = new Timer(25, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) 
        {int value = jb.getValue() + 1;
         jb.setValue(value);}
    });
    jb.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) 
        {if (jb.getValue() == 100) 
            {timer.stop();
             Connecting.dispose();
             login.setVisible(true);}}
    });
    Connecting.add(jb);  
    Connecting.setSize(800,600);  
    Connecting.setLayout(null);  
    Connecting.setVisible(true);
    Connecting.setDefaultCloseOperation(Connecting.EXIT_ON_CLOSE);
    timer.start(); 
//login frame
    al = new JLabel("Account Login");
    al.setBounds(320,90,200,30);
    al.setFont(new Font("Cascadia Code",Font.BOLD,16));
    un = new JLabel("User Name");
    un.setBounds(245,160,200,30);
    tf2 = new JTextField();
    tf2.setBounds(325,160,200,30);
    pw = new JLabel("Password");
    pw.setBounds(245,210,200,30);
    JPasswordField pass = new JPasswordField();
    pass.setBounds(325,210,200,30);
    b6 = new JButton("Submit");
    b6.setBounds(330,270,100,25);
    help = new JLabel("Use your Name as user_name & Date of Birth as Password");
    help.setBounds(220,380,400,70);
//Banking frame    
    greet = new JLabel("Welcome ");
    greet.setBounds(40,30,200,30);
    greet.setFont(new Font("Verdana",Font.BOLD,20));
    uname = new JLabel();
    uname.setBounds(150,30,200,30);
    uname.setFont(new Font("Verdana",Font.BOLD,20));
    bal = new JLabel("Available Balance");
    uname.setText(Name);
    bal.setBounds(350,75,200,30);
    bal.setFont(new Font("Helvetica",Font.BOLD,14));
    tf1 = new JTextField();
    tf1.setBounds(500,75,100,30);
    act = new JLabel("Activity");
    act.setBounds(300,115,200,30);
    ta1 = new JTextArea();
    ta1.setBounds(300,150,300,60);
    det = new JLabel("Details");
    det.setBounds(300,240,200,25);
    ta2 = new JTextArea();
    ta2.setBounds(300,300,300,170);
    JScrollPane scroll = new JScrollPane (ta1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scroll.setBounds(300,150,420,80);
    JScrollPane scroll1 = new JScrollPane (ta2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scroll1.setBounds(300,280,330,200);
    b1 = new JButton("Home");
    b1.setBounds(60,120,120,25);
    b2 = new JButton("Deposit");
    b2.setBounds(60,180,120,25);
    b3 = new JButton("Withdraw");
    b3.setBounds(60,240,120,25);
    b5 = new JButton("Pay Now");
    b5.setBounds(400,500,120,25);
    b7 = new JButton("Exit");
    b7.setBounds(60,300,120,25);
    login.add(al);login.add(un); login.add(pw); login.add(tf2); login.add(pass); login.add(b6); login.add(help);
    frame8.add(greet); frame8.add(uname); frame8.add(bal); frame8.add(act);
    frame8.add(tf1); frame8.add(scroll);frame8.add(scroll1); frame8.add(det);
    frame8.add(b1); frame8.add(b2); frame8.add(b3);   frame8.add(b5); frame8.add(b7);
    ta1.setText(ta1.getText()+"Date"+"\tDescription"+"\tDebit"+"\tCredit"+"\tBalance");
    tf1.setText(String.valueOf(bap.balance()));
    ta2.setText(ta2.getText()+"\nName : " + "\t"+Name);
    ta2.setText(ta2.getText()+"\nCustomer Id : " + "\t"+cusid);
    ta2.setText(ta2.getText()+"\nDOB : " + "\t"+date+"/"+month+"/"+year);
    ta2.setText(ta2.getText()+"\nAddress : " + "\t"+addr);
    ta2.setText(ta2.getText()+"\nPH No : " + "\t"+pno);
    ta2.setText(ta2.getText()+"\nE-Mail : " + "\t"+email);
    ta2.setText(ta2.getText()+"\nTo : " + "\t"+to);
    ta2.setText(ta2.getText()+"\nConsumer Id : "+id);
    ta2.setText(ta2.getText()+"\nTotal : " + "\t"+amttp);
//banking withdraw operation
    String[] accno = new String[2];
    accno[0] = cusid; accno[1] = "---select---";
    JLabel select1 = new JLabel("Select account from : ");
    JLabel ea1 = new JLabel("Enter Amount : ");
    select1.setBounds(300,280,150,25);
    ea1.setBounds(300,330,150,25);
    JTextField eai1 = new JTextField();
    eai1.setBounds(450,330,200,25);
    JComboBox cb2 = new JComboBox(accno);
    cb2.setBounds(450,280,200,25);
    JButton withdraw = new JButton("Withdraw");
    withdraw.setBounds(420,380,100,25);
//banking deposit operation  
    String[] accno2 = new String[1];
    accno2[0] = cusid;
    JLabel select = new JLabel("Select account from : ");
    JLabel ea = new JLabel("Enter Amount : ");
    select.setBounds(300,280,150,25);
    ea.setBounds(300,330,150,25);
    JTextField eai = new JTextField();
    eai.setBounds(450,330,200,25);
    JComboBox cb1 = new JComboBox(accno);
    cb1.setBounds(450,280,200,25);
    JButton deposit = new JButton("Deposit");
    deposit.setBounds(420,380,100,25);
//main bank frame
    String paidsu ="Paid Successfully @ "+ LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +"|"+LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    JLabel paid = new JLabel();
    paid.setText(paidsu);
    paid.setBounds(350,500,350,30);
    if(b5.getModel().isPressed()){b5.repaint();}
    JLabel warn = new JLabel("Amount Exceeds Balance");
    warn.setBounds(400,500,200,30);
    frame8.add(warn);
    warn.setVisible(false); 
    b7.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {frame8.dispose();login.dispose();Connecting.dispose();}
    });
    b6.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            String usrname = tf2.getText();
            String password =  pass.getText();
            if(usrname.equals(name) && password.equals(daob))
            {frame8.setVisible(true);login.setVisible(false);
            //balance checker.
            try{
            String bala = String.valueOf(bap.balance());
            double balanc = Double.parseDouble(bala); 
            if(amt>balanc){JOptionPane.showMessageDialog(frame8,"You don't have sufficient balance!","Alert",JOptionPane.WARNING_MESSAGE);}
            }
            catch(NumberFormatException ime){System.out.println("String was entered :: Invalid Input!");} //balance checker.
            } 
            else{ JOptionPane.showMessageDialog(frame8,"INVALID Username/Password","Alert",JOptionPane.WARNING_MESSAGE); }   
        }
    });
    b5.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            JPanel pin1 = new JPanel();
            JLabel pinlabel = new JLabel("Enter PIN :");
            JPasswordField mpin = new JPasswordField(10);
            pin1.add(pinlabel);
            pin1.add(mpin);
            String[] options = new String[]{"Submit"};
            try{
            String bala = String.valueOf(bap.balance());
            double balanc = Double.parseDouble(bala);
            if(amt>balanc)
            {  JOptionPane.showMessageDialog(frame8,"Deposit amount to account");  
                b5.setVisible(false);warn.setVisible(true); }//b5.setVisible(false);
            else{
            int option = JOptionPane.showOptionDialog(null, pin1, "Authenticator (Use DDMM from DOB)",JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,null, options, options[0]);
            //if(option == 0) // pressing OK button
            String PIN = mpin.getText();
            if(PIN.equals(PIN2))
            {
            bap.WithdrawAmount(amt);
            tf1.setText(String.valueOf(bap.balance()));
            ta1.setText(ta1.getText()+"\n"+LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"\t"+TO+"\t"+amt+"\t---\t"+String.valueOf(bap.balance()));
            b5.setVisible(false);
            frame8.add(paid);
            frame8.remove(b5);
            warn.setVisible(false);
            paid.setVisible(true);}
            else{  JOptionPane.showMessageDialog(frame8,"INVALID PIN!","Alert",JOptionPane.WARNING_MESSAGE); }
            warn.setVisible(false);
            }
            }
            catch(NumberFormatException ime){System.out.println("String was entered :: Invalid Input!");}   
        }
    });
    b3.addActionListener(new ActionListener()
	{
			public void actionPerformed(ActionEvent e)
            {  
                ta2.setVisible(false);scroll1.setVisible(false);b5.setVisible(false);det.setVisible(false);paid.setVisible(false);
                cb1.setVisible(false);select.setVisible(false);ea.setVisible(false);eai.setVisible(false);deposit.setVisible(false);    
                frame8.add(cb2); frame8.add(select1); frame8.add(ea1); frame8.add(eai1); frame8.add(withdraw);warn.setVisible(false);//frame8.add(warn);
                cb2.setVisible(true);select1.setVisible(true);ea1.setVisible(true);eai1.setVisible(true);withdraw.setVisible(true);   eai1.setText("");
                withdraw.addActionListener(new ActionListener()
                {
                public void actionPerformed(ActionEvent e)
                {
                    try{
                    double in1 = Double.parseDouble(eai1.getText());
                    String bala = String.valueOf(bap.balance());
                    double balan = Double.parseDouble(bala);
                    if(in1>balan)
                    {eai1.setText("");warn.setVisible(true);}
                    else{
                    bap.WithdrawAmount(in1);
                    tf1.setText(String.valueOf(bap.balance()));
                    ta1.setText(ta1.getText()+"\n"+LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +"\t"+"self"+"\t"+in1+"\t---\t"+String.valueOf(bap.balance()));
                    eai1.setText("");warn.setVisible(false);}}
                    catch(NumberFormatException ime)
                    {System.out.println("String was entered :: Invalid Input!");eai1.setText("INVALID!");}   
                }
                });
            }
	});
    b2.addActionListener(new ActionListener()
	{
			public void actionPerformed(ActionEvent e)
            { 
                ta2.setVisible(false);scroll1.setVisible(false);b5.setVisible(false);det.setVisible(false);paid.setVisible(false);warn.setVisible(false);               
                cb2.setVisible(false);select1.setVisible(false);ea1.setVisible(false);eai1.setVisible(false);withdraw.setVisible(false); 
                frame8.add(cb1); frame8.add(select); frame8.add(ea); frame8.add(eai); frame8.add(deposit);   eai.setText("");
                cb1.setVisible(true);select.setVisible(true);ea.setVisible(true);eai.setVisible(true);deposit.setVisible(true);
                deposit.addActionListener(new ActionListener()
                {
                public void actionPerformed(ActionEvent e)
                {
                    try{
                    double in2 = Double.parseDouble(eai.getText());
                    bap.DepositAmount(in2);
                    tf1.setText(String.valueOf(bap.balance()));
                    ta1.setText(ta1.getText()+"\n"+LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"\t"+"self"+"\t---\t"+in2+"\t"+String.valueOf(bap.balance()));
                    eai.setText("");warn.setVisible(false);}
                    catch(NumberFormatException ime)
                    {System.out.println("String was entered :: Invalid Input!");eai.setText("INVALID!");}        
                }
                });
            }
	});
    b1.addActionListener(new ActionListener()
	{
			public void actionPerformed(ActionEvent e)
            {   
                cb1.setVisible(false);select.setVisible(false);ea.setVisible(false);eai.setVisible(false);deposit.setVisible(false);
                cb2.setVisible(false);select1.setVisible(false);ea1.setVisible(false);eai1.setVisible(false);withdraw.setVisible(false);
                ta2.setVisible(true);scroll1.setVisible(true);det.setVisible(true);paid.setVisible(true);warn.setVisible(false);
                if(b5.getModel().isPressed())
                {b5.setVisible(false);}
                else{b5.setVisible(true);}
            }
	});
    login.setLayout(null);
    login.setSize(800,600);
    //login.setVisible(true);
    login.setDefaultCloseOperation(frame8.EXIT_ON_CLOSE);
    login.setDefaultCloseOperation(login.EXIT_ON_CLOSE);
    frame8.setLayout(null);
    frame8.setSize(800,600);
    frame8.setDefaultCloseOperation(frame8.EXIT_ON_CLOSE);
    frame8.setDefaultCloseOperation(login.EXIT_ON_CLOSE);
    }
    public void exitb()
    {
        Connecting.dispose();
        login.dispose();
        frame8.dispose();
    }
}
class BankAccountprototype
{
	static double amount;
	double balance = 10000;
	double newbalance;
	public double DepositAmount(double amount)
	{
		try{amountchecker();balance = amount + balance;return balance;}
		catch(InvalidAmountException iae)
		{return 0;}
	}	
	public double WithdrawAmount(double amount)
	{	
		try
		{amountchecker();	
		if(amount > balance){return 0;}
		else{balance = balance - amount;return balance;}}
		catch(InvalidAmountException iae)
		{return 0;}
	} 	
	public double  balance()
	{
        return balance;
	}
	public static void amountchecker() throws InvalidAmountException
	{
		if(amount<0)
		{throw new InvalidAmountException("Invalid input");}
	}	
} 
class InvalidAmountException extends Exception
{	
	public InvalidAmountException(String s)
	{System.out.println(s);}
}
//End
//source file is available on github


