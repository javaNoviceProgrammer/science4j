package latex4j.text;

import latex4j.base.Command;

public class Title {
	
	/*
	 * Title:
	 * 		\title{....}
	 * 		\author{...}
	 * 		\date{...}
	 * 
	 * after \begin{document}
	 * 			\maketitle
	 * 
	 * 
	 */
	
	String title ;
	String author ;
	String date ;
	
	public Title(String title) {
		this.title = title ;
	}
	
	public Title() {
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public void setAuthor(String author) {
		this.author = author;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(50) ;
		if(title!=null)
			sb.append(new Command("title", title)) ; // --> \title{title}
		if(author!=null)
			sb.append(new Command("author", author)) ;
		if(date!=null)
			sb.append(new Command("date", date)) ;
//		else
//			sb.append(new Command("date", "")) ; // --> date{}
		return sb.toString() ;
	}
	
}
