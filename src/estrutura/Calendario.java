/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura;

import java.util.Calendar;

/**
 *
 * @author gibajunior
 */
public class Calendario {
    
    public String getDia(){
        Calendar cal = Calendar.getInstance();
        int num = cal.get(Calendar.DAY_OF_WEEK);
        switch(num){
            case 1: return "Domingo";
            case 2: return "Segunda-feira";
            case 3: return "Terça-feira";
            case 4: return "Quarta-feira";
            case 5: return "Quinta-feira";
            case 6: return "Sexta-feira";
            default: return "Sábado";
        }
    }
    
    public String getData(){
        Calendar cal = Calendar.getInstance();
        String data = String.format("%02d",cal.get(Calendar.DATE))+"/"+String.format("%02d",cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR);
        return data;
    }
    
    public String getPrefix(){
        Calendar cal = Calendar.getInstance();
        String prefix = cal.get(Calendar.YEAR)+""+String.format("%02d",cal.get(Calendar.MONTH)+1)+""+String.format("%02d",cal.get(Calendar.DATE));
        return prefix;
    }
    
    public static void main(String[] args) {
        Calendario cc = new Calendario();
        System.out.println(cc.getDia());
        System.out.println(cc.getData());
        System.out.println(cc.getPrefix());
    }
}
