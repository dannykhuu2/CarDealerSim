//Danny Khuu
//500 903 037
//April 11 2019

import java.util.LinkedList;
import java.util.ListIterator;
public class SalesTeam
{
    private LinkedList<String> SalesT;
    //Constructor method that adds all the sales team members to a linked list
    public SalesTeam()
    {
        LinkedList<String> s = new LinkedList<String>();
        SalesT = s;
        SalesT.addFirst("Danny");
        SalesT.addFirst("John");
        SalesT.addFirst("Jessie");
        SalesT.addFirst("Jasmine");
        SalesT.addFirst("Tina");
        SalesT.addFirst("Kevin");
    }
    //Returns a random sales member 
    public String getSalesMember()
    {
        int index = (int)(Math.random()*6);
        String member = SalesT.get(index);
        return member;
    }
    //returns a string of all the sales members 
    public String displayAll()
    {
        String members = "Team: ";
        ListIterator<String> iterator = SalesT.listIterator();
        for(int i = 0; i < SalesT.size(); i++)
        {
            members = members + iterator.next() + " ";
        }
        return members;
    }
    //Returns the linked list of sales team object
    public LinkedList<String> getMemberList()
    {
        return SalesT;
    }
}