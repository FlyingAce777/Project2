//Chris Acebedo

package project2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Collection 
{
	private ArrayList<ShowInWeek> allData;
	private Random rand;
	private String fileName;


	public Collection(String fn) 
	{
		// TODO Auto-generated constructor stub
		allData = new ArrayList<ShowInWeek>();
		rand = new Random();
		fileName = fn;
		readFile();
	}

	public void addData(ShowInWeek addData)
	{
		allData.add(addData);
	}

	public void purgeShow(String title)
	{
		for (ShowInWeek s : allData) 
		{
			if(s.getShow_titles().equals(title)) 
			{
				s.setShow_titles("*"+title);
			}
		}
	}

	public void unpurgeShow(String title)
	{
		for (ShowInWeek s : allData) 
		{
			if(s.getShow_titles().equals("*" +title)) 
			{
				s.setShow_titles(title);
			}
		}
	}

	public void edit(ShowInWeek edit)
	{
		Iterator<ShowInWeek> i = allData.iterator();

		while (i.hasNext())
		{
			ShowInWeek item = i.next();
			if (item.equals(edit))
			{
				item.setWeek(edit.getWeek());
				item.setCatagory(edit.getCatagory());
				item.setWeekly_rank(edit.getWeekly_rank());
				item.setShow_titles(edit.getShow_titles());
				item.setSeason_title(edit.getSeason_title());
				item.setWeekly_hours_viewed(edit.getWeekly_hours_viewed());
				item.setCumulative_weeks(edit.getCumulative_weeks());
			}
		}
	}

	public String random_suggestion()
	{
		if (allData.size() > 0)
		{
			{
				Iterator<ShowInWeek> i = allData.iterator();
				boolean valid_suggestion = false;
				boolean havent_found_suggestion = true;

				while (i.hasNext())
				{
					ShowInWeek item = i.next();
					if (item.isPurged() == false)
					{
						valid_suggestion = true;
						break;
					}
				}

				if (valid_suggestion == true)
				{
					while (havent_found_suggestion == true)
					{
						int variable = rand.nextInt(allData.size());
						ShowInWeek suggestion = allData.get(variable);

						if (suggestion.isPurged() == false)
						{
							return suggestion.getShow_titles();
						}
					}
				}
			}
		}
		return null;
	}

	public String predictive_show()
	{
		if (allData.size() > 0)
		{
			{
				Iterator<ShowInWeek> i = allData.iterator();
				boolean valid_suggestion = false;
				boolean havent_found_suggestion = true;

				while (i.hasNext())
				{
					ShowInWeek item = i.next();
					if (item.isPurged() == false)
					{
						valid_suggestion = true;
						break;
					}
				}

				if (valid_suggestion == true)
				{
					while (havent_found_suggestion == true)
					{
						int variable = rand.nextInt(allData.size());
						ShowInWeek suggestion = allData.get(variable);

						if (suggestion.isPurged() == false)
						{
							return suggestion.getShow_titles();
						}
					}
				}
			}
		}
		return null;
	}

	public String predictive_showings()
	{

		if (allData.size() > 1)


		{
			int i = rand.nextInt(allData.size());
			ShowInWeek suggestion = allData.get(i);
			ShowInWeek suggestion2 = allData.get(i);

			if (i == (allData.size() - 1))
			{ 
				suggestion2 = allData.get(i - 1);
			}

			else
			{
				suggestion2 = allData.get(i + 1);
			}

			return suggestion.getShow_titles() + " and " +  suggestion2.getShow_titles();
		}

		else
		{
			return null;
		}
	}

	public String getShow(String week)
	{
		Iterator<ShowInWeek> i = allData.iterator();
		String Show_names = "";

		while (i.hasNext())
		{
			ShowInWeek item = i.next();
			if (item.getWeek().equals(week))
			{
				Show_names += item.getShow_titles() + " \n";
			}
		}
		return Show_names;
	}
	
	public String toString() {
		// returns a string representation of this showWeeks
		String toReturn = "";
		for (ShowInWeek s : allData) 
		{
			toReturn += s.toString();
		}	
		return toReturn;
	}
	
	//gets all ShowWeek(s) for a given week
    public ArrayList<ShowInWeek> getOneWeek(String w)
    {
        ArrayList<ShowInWeek> weekList = new ArrayList<ShowInWeek>();
        for (ShowInWeek s : allData) 
        {
            if(s.getWeek().equals(w)) {
                weekList.add(s);
            }
        }
        return weekList;
    }

	private void readFile () 
	{
		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String week = null;
			while ((week = lineReader.readLine())!=null) 
			{		
				String catagory = lineReader.readLine();
				String weekly_rank = lineReader.readLine();
				String show_titles = lineReader.readLine();
				String season_title = lineReader.readLine();
				String weekly_hours_viewed = lineReader.readLine();
				String cumulative_weeks = lineReader.readLine();
				ShowInWeek toAdd = (new ShowInWeek(week, catagory, weekly_rank, show_titles, season_title, weekly_hours_viewed, cumulative_weeks));
				allData.add(toAdd);
			}

		} 

		catch (Exception e) 
		{
			System.err.println("there was a problem with the file reader, try different read type.");
			try 
			{
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String week = null;
				while ((week = lineReader.readLine())!=null) 
				{
					String catagory = lineReader.readLine();
					String weekly_rank = lineReader.readLine();
					String show_titles = lineReader.readLine();
					String season_title = lineReader.readLine();
					String weekly_hours_viewed = lineReader.readLine();
					String cumulative_weeks = lineReader.readLine();
					ShowInWeek toAdd = (new ShowInWeek(week, catagory, weekly_rank, show_titles, season_title, weekly_hours_viewed, cumulative_weeks));
					allData.add(toAdd);
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("there was a problem with the file reader, try again.  either no such file or format error");
			} 
			
			finally {
				if (lineReader != null)
					try 
				{
						lineReader.close();
				} catch (IOException e2) 
				{
					System.err.println("could not close BufferedReader");
				}
			}			
		} finally 
		{
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) 
			{
					System.err.println("could not close BufferedReader");
				}
		}
	} // end of readFile method
	
	public void writeFile () 
	{
		// overloaded method: this calls doWrite with file used to read data
		// use this for saving data between runs
		doWrite(fileName);
	}
	
	public void writeFile(String altFileName) 
	{
		// overloaded method: this calls doWrite with different file name 
		// use this for testing write
		doWrite(altFileName);		
	}// end of writeFile method
	
	public void doWrite(String fn) 
	{
		// this method writes all of the data in the persons array to a file
		try
		{

			FileWriter fw = new FileWriter(fn);
			BufferedWriter myOutfile = new BufferedWriter(fw);			
			
			for (int i = 0; i < allData.size(); i++) 
			{
					myOutfile.write (allData.get(i).getWeek()+"\n");
					myOutfile.write (allData.get(i).getCatagory()+"\n");
					myOutfile.write (allData.get(i).getWeekly_rank()+"\n");
					myOutfile.write (allData.get(i).getShow_titles()+"\n");
					myOutfile.write (allData.get(i).getSeason_title()+"\n");
					myOutfile.write (allData.get(i).getWeekly_hours_viewed()+"\n");
					myOutfile.write (allData.get(i).getCumulative_weeks()+"\n");
			}
			myOutfile.flush();
			myOutfile.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.err.println("Didn't save to " + fn);
		}
	}
}
