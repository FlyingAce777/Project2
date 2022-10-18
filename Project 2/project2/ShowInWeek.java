//Chris Acebedo

package project2;

public class ShowInWeek implements Comparable <ShowInWeek>
{
	private String week;
	private String catagory;
	private String weekly_rank;
	private String show_titles;
	private String season_title;
	private String weekly_hours_viewed;
	private String cumulative_weeks;
	private boolean isPurged;
	
	public ShowInWeek()
	{
		week = null;
		catagory = null;
		weekly_rank = null;
		show_titles = null;
		season_title = null;
		weekly_hours_viewed = null;
		cumulative_weeks = null;
		isPurged = false;
	}
	
	public ShowInWeek(String week, String catagory, String weekly_rank, String show_titles, String season_title, String weekly_hours_viewed, String cumulative_weeks)
	{
		this.week = week;
		this.catagory = catagory;
		this.weekly_rank = weekly_rank;
		this.show_titles = show_titles;
		this.season_title = season_title;
		this.weekly_hours_viewed = weekly_hours_viewed;
		this.cumulative_weeks = cumulative_weeks;
		this.isPurged = false;
	}
	public String getWeek() 
	{
		//returns week
		return week;
	}
	
	public void setWeek(String week) 
	{
		this.week = week;
	}
	
	public String getCatagory() 
	{
		//returns category
		return catagory;
	}
	
	public void setCatagory(String catagory) 
	{
		this.catagory = catagory;
	}
	
	public String getWeekly_rank() 
	{
		//returns weekly ranking
		return weekly_rank;
	}
	public void setWeekly_rank(String weekly_rank) 
	{
		this.weekly_rank = weekly_rank;
	}
	
	public String getShow_titles() 
	{
		//returns show titles 
		return show_titles;
	}
	
	public void setShow_titles(String show_titles) 
	{
		this.show_titles = show_titles;
	}
	
	public String getSeason_title() 
	{
		//returns the season title
		return season_title;
	}
	
	public void setSeason_title(String season_title) 
	{
		this.season_title = season_title;
	}
	
	public String getWeekly_hours_viewed() 
	{
		//returns the hours watched
		return weekly_hours_viewed;
	}
	
	public void setWeekly_hours_viewed(String weekly_hours_viewed) 
	{
		this.weekly_hours_viewed = weekly_hours_viewed;
	}
	
	public String getCumulative_weeks() 
	{
		//returns the cumulative weeks in top 10
		return cumulative_weeks;
	}
	
	public void setCumulative_weeks(String cumulative_weeks) 
	{
		this.cumulative_weeks = cumulative_weeks;
	}
	
	public boolean isPurged() 
	{
		return isPurged;
	}

	public void setPurged(boolean isPurged) 
	{
		this.isPurged = isPurged;
	}
	
	public String toString()
	{
		return week + " " + catagory + " " + weekly_rank + " " + show_titles + " " + season_title + " " + weekly_hours_viewed + " " + cumulative_weeks + " \n";
	}
	
	public boolean equals (ShowInWeek compare)
	{
		if (compare.week.equals(week) && compare.show_titles.equals(show_titles))
		{
			return true;
		}
		
		return false;
	}

	@Override
	public int compareTo(ShowInWeek compare) 
	{
		// TODO Auto-generated method stub
		if (week.compareTo(compare.getWeek()) < 0)
		{
			return -1;
		}
		
		if (week.compareTo(compare.getWeek()) > 0)
		{
			return 1;
		}
		
		if (show_titles.compareTo(compare.getShow_titles()) < 0)
		{
			return -1;
		}
		
		if (show_titles.compareTo(compare.getShow_titles()) > 0)
		{
			return 1;
		}
		
		return 0;
	}
	
}
