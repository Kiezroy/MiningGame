/*

    Everything works except for two problems...

        1). Whenever the guy mines, sometimes the StringBuffer blocky will appear to have a question mark symbol, ?, which is not something that I append at all
            The question mark appears because the emojis are actually 2 characters and appends a question mark + the emoji because it is 2 chars as opposed to the ores
            //Fixed with Normalizer.normalize() java method

        2). Poop is still coming out with question mark
            //FIXED!
            //Basically if the first "ore" is poop, which is two chars hence the substring(2,4) then it deletes (2,4) rather than (2,3) since its an emoji



 */

package com.company;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static int stone = 0, diamond = 0, iron = 0, gold = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

	    System.out.println(" ⚒  Miner  ⚒ \n");
	    System.out.println("   ·Start \n \n   ·About \n \n   ·Exit \n \n   ·Credits \n");

	    System.out.print("Please type your option: ");
	    String option = input.nextLine();
	    mainMenu(input, option);

    }

    public static void mainMenu(Scanner input, String option){
        boolean running = true;
        while(running) {
            if (option.equals("Exit") || option.equals("exit")) { System.exit(0);}
            if(option.equals("Start") || option.equals("start")) { running = false; startGame(); }
            else if (option.equals("Credits") || option.equals("credits")) {
                rollCreds();
                System.out.print("Please type your option: ");
                option = input.nextLine();
                mainMenu(input, option);
            } else if (option.equals("About") || option.equals("about")) {
                System.out.println("Miner is a game where you mine through a cave containing different materials with the tools you have! \n");
                System.out.print("Please type your option: ");
                option = input.nextLine();
                mainMenu(input, option);
            } else {
                System.out.println("Unknown command");
                System.out.print("Please type your option: ");
                option = input.nextLine();
                mainMenu(input, option);
            }
        }

    }

    public static void startGame(){
        //int stone = 0, diamond = 0, iron = 0, gold = 0;
        String person = Normalizer.normalize("\uD83E\uDDCD ", Normalizer.Form.NFD);
        StringBuffer blockys = new StringBuffer(person + "▯▯▯▯▯▯▯▯▯");

        Scanner input = new Scanner(System.in);

        System.out.println("\nWelcome to Miner! Collect as many ores as possible! Exit anytime by typing Exit ... Make sure to CAPITALIZE the first letter!!!!");
        System.out.println("For controls, type C \nFor Inventory, type I\nFor Ore Symbols, type O\nTo Mine, type M \n");

        System.out.println(person + "▯▯▯▯▯▯▯▯▯" + "\n");

        String user = input.next();
        boolean game = true;

        while(game) {
            switch (user) {
                case "C":
                    System.out.println(" Controls: C \n Inventory: I \n Ore Symbols: O \n To Mine: M \n To Exit: Exit");
                    user = input.next();
                    break;

                case "I":
                    display();
                    user = input.next();
                    break;

                case "O":
                    symbols();
                    user = input.next();
                    break;

                case "M":
                    System.out.println(mine(blockys));
                    user = input.next();
                    break;

                case "Exit":
                    game = false;
                    System.exit(0);
                case "exit":
                    game = false;
                    System.exit(0);
                default:
                    System.out.println("Unknown command!\n");
                    user = input.next();
            }
        }
    }

    public static void symbols(){
        System.out.println("Stone: ▯ \nDiamond: ♦\nIron: ⎉\nGold: ⏣\nPoop: \uD83D\uDCA9");
    }

    public static StringBuffer mine(StringBuffer blockys){
        addOre(blockys);

        //FIXED MY LAST PROBLEM OF THE POOP YAY
        //Basically if the first "ore" is poop, which is two chars hence the substring(2,4) then it deletes (2,4) rather than (2,3) since its an emoji
        if(blockys.substring(2,4).equals("\uD83D\uDCA9")){
            blockys.delete(2, 4);
        }

        blockys.delete(2, 3);

        int randBlock = (int) (Math.random() * 101) + 0;

        if(randBlock <= 50){
            //50% chance of stone appearing
            blockys.append("▯");
        }
        else if(randBlock > 50 && randBlock <= 60){
            //10% chance of gold
            blockys.append("⏣");
        }
        else if(randBlock > 60 && randBlock <= 80){
            //20% chance of iron
            blockys.append("⎉");
        }
        else if(randBlock > 80 && randBlock <= 90){
            //10% chance of poop
            String poop = Normalizer.normalize("\uD83D\uDCA9", Normalizer.Form.NFD);
            blockys.append(poop);
        }
        else if (randBlock > 90 && randBlock <= 95){
            //5% chance of diamond
            blockys.append("♦");
        }
        else{
            //add bomb 5% chance
            String dynamite = Normalizer.normalize("\uD83E\uDDE8", Normalizer.Form.NFD);
            blockys.append(dynamite);
        }


        return blockys;
    }

    public static void addOre(StringBuffer blockys){
        String str = blockys.substring(2, 3);

        if(blockys.substring(2,4).equals("\uD83E\uDDE8")) {
            System.out.println("SURPRISE! You mined a bomb! BOOOOOOOM! You died!");
            System.exit(0);
        }

        switch(str){
            case "▯":
                stone++;
                break;
            case "♦":
                diamond++;
                break;
            case "⎉":
                iron++;
                break;
            case "⏣":
                gold++;
                break;
        }
    }

    public static void display(){
        System.out.println("StoneAMT: " + stone + "\nDiamondAMT: " + diamond + "\nGoldAMT: " + gold + "\nIronAMT: " + iron);
    }
    public static void rollCreds(){
        String[] emojis = {"\uD83D\uDE0E", "\uD83D\uDE0D", "\uD83D\uDE02", "\uD83D\uDE2D"};
        for(int i = 0; i < 100; i++) {
            int rand = (int) (Math.random() * 4);
            System.out.println("Kyo Nguyen " + emojis[rand]);
        }
    }
}
