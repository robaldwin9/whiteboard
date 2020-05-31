package whiteboard.graphics;

public class Main 
{
	/**
	 * Entry point 
	 * 
	 * @param args - application uses no arguments
	 */
	public static void main(String args[])
	{
		/** Initialize MVC application */
		 new Controller(new View(), new Model());
	}
}
