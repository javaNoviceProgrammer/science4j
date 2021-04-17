package latex4j.bib;

import java.util.ArrayList;
import java.util.List;

public class ArticleBib extends BibItem {
	
	/*
	 * 
	 * @article{hayashi1979new,
	 *	  title={New general relativity},
	 *	  author={Hayashi, Kenji and Shirafuji, Takeshi},
	 *	  journal={Physical Review D},
	 *	  volume={19},
	 *	  number={12},
	 *	  pages={3524},
	 *	  year={1979},
	 *	  publisher={APS}
	 *	}
	 * 
	 * 
	 */
	
	List<String> authorsFirstName ;
	List<String> authorsMiddleName ;
	List<String> authorsLastName ;

	public ArticleBib(String keyword) {
		super("article", keyword);
		authorsFirstName = new ArrayList<>() ;
		authorsMiddleName = new ArrayList<>() ;
		authorsLastName = new ArrayList<>() ;
	}
	
	public ArticleBib title(String title) {
		addField("title", title) ;
		return this ;
	}
	
	public ArticleBib journal(String journal) {
		addField("journal", journal) ;
		return this ;
	}
	
	public ArticleBib volume(int volume) {
		addField("volume", volume+"") ;
		return this ;
	}
	
	public ArticleBib number(int number) {
		addField("number", number+"") ;
		return this ;
	}
	
	public ArticleBib pages(String pages) {
		addField("pages", pages) ;
		return this ;
	}
	
	public ArticleBib year(int year) {
		addField("year", year+"") ;
		return this ;
	}
	
	public ArticleBib publisher(String publisher) {
		addField("publisher", publisher) ;
		return this ;
	}
	
	public ArticleBib authors(String... authors) { // style string: "firstName   L.   lastname"
		for(String author : authors) {
			String[] parts = author.strip().split("\s{1,10}") ; ;
			if(parts.length==2) { // no middle name
				authorsFirstName.add(parts[0].strip()) ;
				authorsLastName.add(parts[1].strip()) ;
			}
			else if(parts.length==3) { // there is middle name
				authorsFirstName.add(parts[0].strip()) ;
				authorsMiddleName.add(parts[1].strip()) ;
				authorsLastName.add(parts[2].strip()) ;
			}
			else {
				throw new IllegalArgumentException("at least First name and Last name must be specified") ;
			}
		}
		// format a field inside the author={arg}
		// style = lastname, firstname
		List<String> names = new ArrayList<>() ;
		for(int i=0; i<authors.length; i++) {
			names.add("%s, %s".formatted(authorsLastName.get(i), authorsFirstName.get(i))) ;
		}
		String allAuthors = String.join(" and ", names) ;
		addField("author", allAuthors) ;
		return this ;
	}
	

}
