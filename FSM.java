
package fsm1;
import java.util.*;



public class FSM1 { 
    
  
    private static int Confidence = 5;
    private static int Energy = 5;
 
    
    
    
    public static abstract class State {
        protected Scanner input = new Scanner(System.in);
        protected Random random = new Random();
        public State() {}
        public abstract void enter();
        public abstract void exit();
        public abstract int updateAndCheckTransitions();
    }
  /**
     * This class represents the state in which the superHero
     * is about to get star. It transitions to 1 for running, 2 for jumping, 0 for repeating 
     * same action again.
     */
      
 public static class gettingStar extends State {
         public gettingStar() {
             super();
         }

        @Override
        public void enter() {
            System.out.println("superHero is looking for star");
        }

        @Override
        public void exit() {
            System.out.println("************************");
        }

        @Override
        public int updateAndCheckTransitions() {
             int num = 0;
             String isGetting;
             System.out.println("Is  superHero able to find star ? Y or N");
            Scanner scan = new Scanner(System.in);
            isGetting = scan.nextLine();
            if(isGetting.equals("Y")) {
              
                System.out.println(" superHero have enough energy");
                return 2;        
            }
            else if (isGetting.equals("N")) {
                return 1;    /***SuperHero run for the star*/
            }
            else {
                System.out.println("superHero again searching for star");
                updateAndCheckTransitions();
            }
            return 0;
        }
    }
 /**
     * This class represents the state in which the superHero
     * is about to run. It transitions to 3 for hit wall, 2 for jumping, 0 for start
     * in the process of getting star again.
     */
      
        
        public static class superheroRunning extends State {
         public superheroRunning() {
             super();
         }

       @Override
        public void enter() {
            System.out.println("superHero is running");
        }

       @Override
        public void exit() {
            System.out.println(" superHero stopped running");
        }

       @Override
        public int updateAndCheckTransitions() {
            int num = 0; 
             String isGetting;
            System.out.println("Did superHero saw something? Y or N");
            Scanner scan = new Scanner(System.in);
             isGetting = scan.nextLine();
            if(isGetting.equals("Y")) {
            System.out.println("What did superHero Saw?");
            System.out.println("Choices:");
            System.out.println("1. Wall");
            System.out.println("2. Star");
            System.out.println("3. Enemy"); 
            }
              int randomNum = random.nextInt(3);
            
        
            if(randomNum == 1) {
                System.out.println("1. superHero try to maintain distance from the wall");
                 return 3;
            }
            else if(randomNum == 2) {
                System.out.println(" 2.superHero got the star");
                 return 2;
              
            }
            else {
                System.out.println("3.superHero try to maintain distance from enemy");
               return 0;
            }
        }
          
    } 
   
 /**
     * This class represents the state in which the superHero
     * is to sleep. It transitions to 4 to repeat the sleeping process,  0 for start
     * in the process of getting star again.
     */
      
      public static class Sleep extends State {
         public Sleep() {
             super();
         }

        @Override
        public void enter() {
            System.out.println("The superHero is sleeping");
        }

        @Override
        public void exit() {
            System.out.println("The superHero just woke up");
        }

        @Override
        public int updateAndCheckTransitions() {
            int num = 0;
            System.out.println("Is the superHero is still sleeping? Y or N");
            String s;
            s = input.nextLine();
            if(s.equals("Y")) {
                 Energy += 5;
                   return 0; /** looking for star again*/
                   

            }
            else {
                Scanner scan = new Scanner(System.in);
                System.out.println("SuperHero is going to sleep");
                System.out.println("Enter Y to Continue");
                String pt = scan.nextLine();
                if(pt.equals("Y")) {
                    num = 4;
                }
            }
             return num;
        }
    }
      /**
     * This class represents the state in which the superHero
     * is jump when he got the star. It transitions to 4 for sleep, 1 for running, 2 for start
     * in the process of jump again.
     */
        
         public static class superheroJump extends State {
         public superheroJump() {
             super();
         }

        @Override
        public void enter() {
            System.out.println("superhero is jumping");
        }

        @Override
        public void exit() {
            System.out.println("superhero not jumping");
        }

        @Override
        public int updateAndCheckTransitions() {
            int num ;
            System.out.println("How energetic superHero is?");
            Scanner scan = new Scanner(System.in);
            num = scan.nextInt();
            if(num > 5)  {
                System.out.println("superHero is still jumping");
         return 4;
            }
            else if(num < 5) {
                System.out.println("superHero  stopped jumping");
                return 1;
            }
            else {
                System.out.println("superHero no need of star for now");
               return 2;
            }
            
        
        } }
         
        
         /**
     * This class represents the state in which the superHero
     * is about to get hit from the wall.  1 for running, 3 for start
     * in the process of hit wall again.
     */
        
    
   
     public static  class hitWall extends State {
        public hitWall () {
            super();
        }

        @Override
        public void enter() {
            System.out.println("superHero is near the wall looking for star");
        }

        @Override
        public void exit() {
            System.out.println("super Hero is away from wall");
        }

        @Override
        public int updateAndCheckTransitions() {
            int superheroDistance;
            int num = 0;
            System.out.println("Is superHero still nearby the wall ?");
            System.out.println("How near the superHero is from wall ??");
            System.out.println("Greater than 5 is safe and less that 5 is danger for SuperHero");
            System.out.println("Enter the superHero disatnace:");
            superheroDistance = input.nextInt();
            
            if(superheroDistance > 5) {
              
                System.out.println("superHero is safe from hitting the wall");
               
                Confidence += 1;
                return 1;
               
            }
            else if(superheroDistance >=1 && superheroDistance < 5) {
                System.out.println("superHero is in danger");
               
                Scanner scan = new Scanner(System.in);
             
                System.out.println("Enter Y to Continue");
                String sT = scan.nextLine();
                if(sT.equals("Y")) {
                 
                 return 3;
                }
              
            }
            return 3;
        }
    } 
    /**
     * Main driver for FSM
     */
    public static void main(String[] args) {
        /* IMPORTANT! Change the 3 to the number of states you have ***/
        int numberOfStates = 5;
        State[] states = new State[numberOfStates];

        /* IMPORTANT! Modify this code to create each of your states ***/
           states[0] = new gettingStar();
           states[1] = new superheroRunning();
          states[2] = new superheroJump();
          states[3] = new hitWall();
          states[4] = new Sleep();
      
       

        /**** End of code to modify ****/

        int currentState = 0;
        int nextState;  

        states[0].enter();
        while(true) {
            nextState = states[currentState].updateAndCheckTransitions();
            if (nextState != currentState) {
                states[currentState].exit();
                currentState = nextState;
                states[currentState].enter();
            }
        }
    }
} 
