package me.louiletsplaypro.easycmds.utils;

import me.louiletsplaypro.easycmds.Easycmds;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardUtilitys {

    //*
    //Deine Treffer: 0
    //Dein Rang: 1
    //*
    //Toplist:
    //Toplist[1].playerName + " " + Toplist[1].hits
    //Toplist[2].playerName + " " + Toplist[2].hits
    //Toplist[3].playerName + " " + Toplist[3].hits
    //Toplist[4].playerName + " " + Toplist[4].hits
    //Toplist[5].playerName + " " + Toplist[5].hits
    //Toplist[6].playerName + " " + Toplist[6].hits
    //Toplist[7].playerName + " " + Toplist[7].hits
    //Toplist[8].playerName + " " + Toplist[8].hits
    //Toplist[9].playerName + " " + Toplist[9].hits
    //Toplist[10].playerName + " " + Toplist[10].hits

    public static Scoreboard getBaseScoreboard(String playerName){
        Scoreboard s = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = s.registerNewObjective("main", "main", ChatColor.BLUE + "Schneeball Game");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        int treffer = 0;
        treffer = Easycmds.getPlugin().getConfig().getInt("snowball.score." + playerName);
        objective.getScore(ChatColor.MAGIC + "").setScore(15);
        objective.getScore(ChatColor.GRAY + "Deine Treffer: " + ChatColor.RED + treffer).setScore(14);
        objective.getScore(ChatColor.RESET + "").setScore(13);
        objective.getScore(ChatColor.GOLD + "By LouiLetsPlayPro").setScore(12);
        return s;
    }
}
