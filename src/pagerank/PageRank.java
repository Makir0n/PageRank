/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PageRank {

    public static void main(String[] args) {

        ArrayList<Integer> totalPage = new ArrayList<Integer>();
        ArrayList<Integer> tosID = new ArrayList<Integer>();
        ArrayList<Integer> fromsID = new ArrayList<Integer>();

        String jdbc_url = "jdbc:mysql://localhost/DBname";
        String user = "name";
        String password = "pass";
        ResultSet rsLink;
        
        try (Connection con = DriverManager.getConnection(jdbc_url, user, password);
                Statement stmt = con.createStatement()) {
            //id同士の関連
            rsLink = stmt.executeQuery("SELECT * FROM page INNER JOIN pagelinks ON page.page_title = pagelinks.pl_title;");
            while (rsLink.next()) {
                tosID.add(rsLink.getInt("page_id"));
                fromsID.add(rsLink.getInt("pl_from"));
            }
            //indexとpage_idの対応付け
            rsLink = stmt.executeQuery("SELECT * FROM page");
            for (int i = 0; rsLink.next() == true; i++) {
                totalPage.add(rsLink.getInt("page_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //行列でに対応できるようにidをindex同士の関係にする
        ArrayList<Integer> tos = new ArrayList<Integer>();
        ArrayList<Integer> froms = new ArrayList<Integer>();
        for (int i = 0; i < tosID.size(); i++) {
            tos.add(totalPage.indexOf(tosID.get(i)));
        }
        for (int i = 0; i < fromsID.size(); i++) {
            froms.add(totalPage.indexOf(fromsID.get(i)));
        }
        CulMetrix cm = new CulMetrix(tos, froms, totalPage.size());
        Sort st = new Sort(cm.getValues());
        ArrayList<Page> pageRank = st.getBestID();//<Page>いらんのか
        //indexをidに戻す
        ArrayList<Page> pageRankID = pageRank;
        for (int i = 0; i < pageRank.size(); i++) {
            //index番号をtotalのindexに指定すると中身のidが
            int transID = totalPage.get(pageRank.get(i).getPageIndex());
            pageRankID.get(i).setPageId(transID);
        }
        
         for (int i = 0; i < pageRankID.size(); i++) {
         System.out.print(pageRankID.get(i).getId());
         System.out.println("score:" + pageRankID.get(i).getValue());
         }
    }
}
