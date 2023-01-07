//package exam_seating;
import java.util.*;
import javax.swing.*;

interface database{
    static Vector<Institute> institute_vector = new Vector<Institute>();
    static Vector<Student> student_vector=new Vector<Student>();
}

class Student extends JFrame implements database {
    static JFrame error_frame2= new JFrame("Error");
    static JFrame taken= new JFrame("taken");
    String student_name;
    String centre_choice1,centre_choice2,centre_choice3;
    String susername,spassword;
    String alloted;
    String roll_num;
    Student(String name,String uname,String pass,String choice_1,String choice_2,String choice_3 ){
        student_name=name;
        susername=uname;
        spassword=pass;
        centre_choice1=choice_1;
        centre_choice2=choice_2;
        centre_choice3=choice_3;
        student_vector.add(this);
        Allocate();
        System.out.println("COLLEGE ALLOTED="+alloted);
        /*if(usernameTaken(uname)==false){
            student_vector.add(this);
        }*/   
    }
    void Allocate(){
        try{

        for(Institute element:institute_vector){
            if(element.location.equalsIgnoreCase(centre_choice1)){
  
                System.out.println("CHOICE 1: count:"+element.count+"capacity:"+element.capacity);
                if(element.count<element.capacity){
                    this.alloted=element.centre_name;
                    element.add_to_roll++;
                    this.roll_num=""+(((element.insti_code)*1000)+element.add_to_roll);

                    element.count++;
                    System.out.println("Element ka count after adding to students_Allocated ="+element.count);

                    return; 
                }

            }

        }

        for(Institute element:institute_vector){
            if(element.location.equalsIgnoreCase(centre_choice2)){
                System.out.println("CHOICE 2: count:"+element.count+"capacity:"+element.capacity);

                if(element.count<element.capacity){
                    this.alloted=element.centre_name;
                    element.add_to_roll++;
                    this.roll_num=""+(((element.insti_code)*1000)+element.add_to_roll);

                    element.count++;
                    return;
                }
            }
        }
        for(Institute element:institute_vector){
            if(element.location.equalsIgnoreCase(centre_choice3)){
                System.out.println("CHOICE 3: count:"+element.count+"capacity:"+element.capacity);

                if(element.count<element.capacity){
                    this.alloted=element.centre_name;
                    element.add_to_roll++;
                    this.roll_num=""+(((element.insti_code)*1000)+element.add_to_roll);

                    element.count++;
                    return;
                }
            }
        }
 
        throw new OverflowException();
    }
        catch(OverflowException e){
            JOptionPane.showMessageDialog(error_frame2, "Institute could not be allocated: please contact administrator for more details.","Error!", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(error_frame2, "Unexpected error: please contact administrator for more details.","Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    static boolean usernameTaken(String username){

        boolean taken=false;
        
        for(Student student:student_vector){


                if(student.susername.equals(username)){
                    
                    taken=true;
                   
                    JOptionPane.showMessageDialog(error_frame2, "Username is taken! Try different username","Error!", JOptionPane.ERROR_MESSAGE);
                }
                
            }
            return taken;
        }
    static boolean verifyuser(String username,String password){

        boolean verified=false;
            for(Student student:student_vector){
                
                if(student.susername.equals(username)==true){
                    if(student.spassword.equals(password)==false){
                       
                        break;
                    }
                    else{
                        System.out.println("true ke upar");
                        verified=true;
                        break;
                    }
                }
                else{
                   
                    
                }
            }

        return verified;
    }
}


class Institute extends JFrame implements database{
    static Vector<String> location_list=new Vector<>();//list of locations obtained
    
    String centre_name;
    JTable jt;
    String location;
    int capacity;
    int insti_code;
    int count=0;
    int add_to_roll=610;

    static JFrame error_frame= new JFrame("Error");
    JFrame f;
    Institute(String centre_name,String location,int capacity){
        int j=institute_vector.size();
        this.capacity=capacity;
        this.centre_name=centre_name;
        this.location=location;
        institute_vector.add(this);
        if(location_list.contains(location)==false){
          
            location_list.add(location);
        }
        insti_code=110+j;//3 digit
        ++j;
    }

    void print_list(){
     
        f=new JFrame(centre_name);
        String column[]={"ROLL NO.","STUDENT NAME"};

        String data[][]=new String[count][];

        int i=0;
        try{
            for(Institute element:institute_vector){ 
                if(centre_name.equalsIgnoreCase(element.centre_name)){
                    for(Student st:student_vector){
                        if(st.alloted.equals(centre_name)){
 
                            String toAdd[]={st.roll_num,st.student_name};
                            data[i]=toAdd;
                            i++;
                            
                        }
                    }break;
                }}

            jt=new JTable(data,column);    
            jt.setBounds(30,40,200,300);          
            JScrollPane sp=new JScrollPane(jt);    
            f.add(sp);          
            f.setSize(300,400);    
            f.setVisible(true);   
        }
        catch(Exception e){

            System.out.println(e);
            JOptionPane.showMessageDialog(error_frame, "Unknown error, try again or contact admin.","Unknown!", JOptionPane.ERROR_MESSAGE);
        }

    }
}

class OverflowException extends Exception{
    public String toString(){
        return "Msg to Admin:All institutes are filled. Need more institues!";
    }
}


