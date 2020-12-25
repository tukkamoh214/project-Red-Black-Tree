package com.jetbrains;

import org.jetbrains.annotations.NotNull;

import javax.lang.model.type.NullType;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        Form myForm = new Form();
        JFrame jf = new JFrame();
        jf.add(myForm.getMainPanel());
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}



    // fe java menf3sh t7oty function bara class
// lazm kol el functions bt3tk tkon gwa class mo3en fa lazm t7oty el functions dol fe class l2n java based 3ala OOP concept
// fhmtk
// 7awly kda t7otehm fe class kber w ykono abl el main class
// y3ni 23ml class kbir yt7t gwah kol l functions dy! yesss homa m3molhm cut 3shan mtftkresh any 3mlt delete shit na kda m7taga arbthom b b3d
// 3shan kda java re5ma