
public class Monster extends Encounter{
	@Override
	  public void encounter(String name, River river, int row, int col) {
		System.out.println("\n                _ ___                /^^\\ /^\\  /^^\\_");
	        System.out.println("    _          _@)@) \\            ,,/ '` ~ `'~~ ', `\\.");
	        System.out.println("  _/o\\_ _ _ _/~`.`...'~\\        ./~~..,'`','',.,' '  ~:");
	        System.out.println(" / `,'.~,~.~  .   , . , ~|,   ,/ .,' , ,. .. ,,.   `,  ~\\_");
	        System.out.println("( ' _' _ '_` _  '  .    , `\\_/ .' ..' '  `  `   `..  `,   \\_");
	        System.out.println(" ~V~ V~ V~ V~ ~\\ `   ' .  '    , ' .,.,''`.,.''`.,.``. ',   \\_");
	        System.out.println("  _/\\ /\\ /\\ /\\_/, . ' ,   `_/~\\_ .' .,. ,, , _/~\\_ `. `. '.,  \\_");
	        System.out.println(" < ~ ~ '~`'~'`, .,  .   `_::: \\_ '      `_/ ::: \\_ `.,' . ',  \\_");
	        System.out.println("  \\ ' `_  '`_    _    ',/ _::_::_ \\ _    _/ _::_::_ \\   `.,'.,`., \\-,-,-,_,_,");
	        System.out.println("   `'~~ `'~~ `'~~ `'~~  \\(_)(_)(_)/  `~~' \\(_)(_)(_)/ ~'`\\_.._,._,'_;_;_;_;_;\n");
	
	        System.out.println(name + " have encountered the Terminator Alligator!");
            if (col - 3 >0) {
                river.getBoat(name).setCol(col-3);
                System.out.println(name + " was moved backwards by 3 steps!");
            }
            else if (col == 0){
            	System.out.println("Pathetic! Terminator Alligator caused no effect on " + name +"!" );
            }	
            else {	
            	river.getBoat(name).setCol(0);
                System.out.println(name + " was moved to the first column!");
            }
	}
}
