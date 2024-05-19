import javax.swing.JOptionPane;

abstract class baseCamp{

    public static char[] ecuatie =new char[225];
    public static int i=0, ecuatieSize=0;
    public static double rezultatFinal=0;

}

class calculate extends baseCamp{

    double expresie(){
        double rezultat=termen();

        while (i<ecuatieSize && (ecuatie[i]=='+' || ecuatie[i]=='-')) {
            if (ecuatie[i] == '+') {
                i++;
                rezultat += termen();
            } else {
                i++;
                rezultat -= termen();
            }
        }
        return rezultat;
    }

    double termen(){
        double rezultat = factor();
        while (i<ecuatieSize && (ecuatie[i] == '*' || ecuatie[i] == '/')) {
            if (ecuatie[i] == '*') {
                i++;
                rezultat *= factor();
            } else {
                i++;
                rezultat /= factor();
            }
        }
        return rezultat;
    }

    double factor(){
        double rezultat;
        if (i<ecuatieSize && ecuatie[i] == '(') {
            i++;
            rezultat = expresie();
            i++;
        } else {
            rezultat = constanta();
        }
        return rezultat;
    }

    double constanta(){
        double rezultat=0, dotIndicator=0;
        boolean arePunct=false;

        while (i<ecuatieSize && ((ecuatie[i] >= '0' && ecuatie[i]<='9') || ecuatie[i]=='.')) {

            if(ecuatie[i]=='.'){
                arePunct=true;
            }
            else {
                rezultat = rezultat*10 + ecuatie[i] - '0';
            }
            if(arePunct){
                dotIndicator++;
            }

            i++;
        }

        dotIndicator--;
        while(dotIndicator>0){
            rezultat/=10;
            dotIndicator--;
        }

        return rezultat;
    }

}

class inputOutput extends baseCamp{

    static void citire() {

        String input = JOptionPane.showInputDialog("Intrdu ecuatia:");
        try {
            ecuatie = input.toCharArray();
            ecuatieSize=ecuatie.length;
        }
        catch(Exception E){
            JOptionPane.showMessageDialog(null,"Introducerea Datelor a Esuat...", "", JOptionPane.WARNING_MESSAGE);
            System. exit(0);
        }

    }

    static void afisare(){

        if((int)rezultatFinal==rezultatFinal)JOptionPane.showMessageDialog(null,(int)rezultatFinal, "", JOptionPane.INFORMATION_MESSAGE);
        else JOptionPane.showMessageDialog(null,rezultatFinal, "", JOptionPane.INFORMATION_MESSAGE);
    }



}

public class Main extends baseCamp{

    static calculate obiectDeCalcul= new calculate();

    public static void main(String[] args) {

        inputOutput.citire();

        try {
            rezultatFinal = obiectDeCalcul.expresie();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Calcularea Nu a Avut Succes...", "", JOptionPane.WARNING_MESSAGE);
            return;
        }

        inputOutput.afisare();
    }
}