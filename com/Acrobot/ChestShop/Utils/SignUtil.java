package com.Acrobot.ChestShop.Utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

/**
 * @author Acrobot
 */
public class SignUtil {

    public static boolean isSign(Block block){
        return (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN);
    }

    public static boolean isAdminShop(String owner){
        return owner.toLowerCase().replace(" ", "").equals("adminshop");
    }
    
    public static boolean isValid(Sign sign){
        return isValid(sign.getLines());
    }
    public static boolean isValid(String[] lines){
        try{
            String line1 = lines[0];
            String line2 = lines[1];
            String line3 = lines[2];
            String line4 = lines[3];
            return !line1.contains("[") && !line1.contains("]") && !line4.isEmpty() && Numerical.isInteger(line2) && (line3.contains("B") || line3.contains("S"));
        } catch (Exception e){
            return false;
        }
    }

    public static float buyPrice(String text){
        text = text.replace(" ", "").toLowerCase();

        int buyPart = (text.contains("b") ? 0 : -1);
        if(buyPart == -1){
            return -1;
        }
        text = text.replace("b", "").replace("s", "");
        String[] split = text.split(":");
        if(Numerical.isFloat(split[0])){
            float buyPrice = Float.parseFloat(split[0]);
            return (buyPrice != 0 ? buyPrice : -1);
        }else if(split[0].equals("free")){
            return 0;
        }
        
        return -1;
    }

    public static float sellPrice(String text){
        text = text.replace(" ", "").toLowerCase();

        int sellPart = (text.contains("b") && text.contains("s") ? 1 : (text.contains("s") ? 0 : -1));
        text = text.replace("b", "").replace("s", "");
        String[] split = text.split(":");
        
        if(sellPart == -1 || (sellPart == 1 && split.length < 2)){
            return -1;
        }

        if(Numerical.isFloat(split[sellPart])){
            Float sellPrice = Float.parseFloat(split[sellPart]);
            return (sellPrice != 0 ? sellPrice : -1);
        }else if(split[sellPart].equals("free")){
            return 0;
        }
        return -1;
    }

    public static int itemAmount(String text){
        if(Numerical.isInteger(text)){
            return Integer.parseInt(text);
        } else{
            return 0;
        }
    }
}