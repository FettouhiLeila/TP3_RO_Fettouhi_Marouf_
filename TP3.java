import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;
import ilog.concert.*;
import ilog.cplex.*;
import java.util.Scanner;
public class Sacados {
    public static void tri_decroissant(int[][] tab)
    {
        for (int i = 0; i < tab.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < tab.length; j++)
            {
                if (tab[j][2] >tab[index][2]){
                    index = j;
                }
            }
            for(int j=0;j<3;j++) {
                int min = tab[index][j];
                tab[index][j] = tab[i][j];
                tab[i][j] = min;
            }
        }
    }
    
    //fonction1
    static void affichageTab(int[][] tab){
        for(int i=0; i < tab.length; i++)
        {
            System.out.print( "c: "+tab[i][0] );
            System.out.print( " p: "+tab[i][1] );
            System.out.print( " c/p: "+tab[i][2] );

            System.out.print( " \n ");
        }
        System.out.println();
    }
    
    //fonction2
    public static void fonct(int p , int r, int tb [][]) {



        try {
            // nouveau modèle
            IloCplex opl = new IloCplex();
            IloNumVar[][] m = new IloNumVar[r][1];
            for(int i=0;i<r;i++) {

                m[i][0]= opl.numVar(0,p);

            }
            //maximum
            IloLinearNumExpr objective = opl.linearNumExpr();
            for (int i=0; i<p; i++) {

                objective.addTerm(tb[i][0],m[i][0]);

            }
            opl.addMaximize(objective);
            // contraintes

            //permutation de matrice x
          IloNumVar[][] tp = new IloNumVar[r][r];

            for(int i=0;i<r;i++) {
                tp[i][0]=m[i][1];
                tp[i][1]=m[i][0];

            }

            for (int i=0; i<r; i++) {
                opl.addLe(opl.sum(tp[i]),p);

            }


            opl.solve();
            System.out.println(" Fonction objective : "+opl.getObjValue() +"\n");

            System.out.println(" Variables de décision:  ");
            for (int i=0;i<p;i++) {
                System.out.println("\n" );
                for (int j=0; j<p; j++) {

                    System.out.println( "X"+i+ ""+j+"= "+ opl.getValue(m[i][j]));}
            }

            opl.end();

        }catch(IloException exc){
            System.out.print("Exception lev�e " + exc);
        }
    }
            //Main 
             public static void main(String[] args) {
        // TODO Auto-generated method stub

        Scanner sc=new Scanner(System.in);
        System.out.print(" Saisir poid maximum : ");
        int p=sc.nextInt();
        System.out.print(" Saisir le nombre d'objets :");
        int r=sc.nextInt();

        int [][]tab=new int[o][3];


        for(int i=0;i<r;i++){

            System.out.println("c " +i );

            tab[i][0]=sc.nextInt();
            System.out.println("p " +i );

            tab[i][1]=sc.nextInt();

            tab[i][2]=tab[i][0]/tab[i][1];


        }


        //tri décroissant 
        tri_decroissant(tab);
        //Affichage du tableau après le tri
        affichageTab(tab);

        int [][]t=new int[r][2];
        for(int i=0;i<r;i++) {
            t[i][0]=tab[i][0];
            t[i][1]=tab[i][1];
        }

     fonct (p,r,t);

    }
}
