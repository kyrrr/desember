package no.webbydebby.chooser;

import java.util.List;

public interface ChooseFromListOrOtherDefinition {
	
	/**
	 * Skal returnere en av entriene i listern, eller en helt ny
	 * @param choices
	 * @return
	 */
	String chooseFromListOrOther(List<String> choices);

}
