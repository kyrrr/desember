package no.webbydebby.chooser;

import java.util.List;

public class AlwaysChooseFirstImpl implements ChooseFromListOrOtherDefinition{

	@Override
	public String chooseFromListOrOther(List<String> choises) {
		if (choises==null||choises.size()==0) return "FIRST";
		return choises.get(0);
	}

}
