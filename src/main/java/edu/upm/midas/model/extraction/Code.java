package edu.upm.midas.model.extraction;

import java.util.LinkedList;

public class Code {
	public LinkedList<String> keys;

	public Code(LinkedList<String> keys) {
		this.keys = keys;
	}

	public LinkedList<String> getKeys() {
		return this.keys;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Code)) {
			return false;
		}
		LinkedList<String> extKeys = ((Code) obj).getKeys();
		for (int i = 0; i < this.keys.size(); i++) {
			for (int j = 0; j < extKeys.size(); j++) {
				String extKey = extKeys.get(j);
				String key = this.keys.get(i);
				if (extKey.equalsIgnoreCase(key)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int ret = 0;
		for (int i = 0; i < this.keys.size(); i++) {
			ret ^= this.keys.get(i).hashCode();
		}
		return ret;
	}

}