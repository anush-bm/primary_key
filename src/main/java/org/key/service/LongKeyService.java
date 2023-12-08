/**
 * 
 */
package org.key.service;

import io.hypersistence.tsid.TSID;

/**
 * @author anushbm
 *
 */
public class LongKeyService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(TSID.fast().toLong());
	}

}
