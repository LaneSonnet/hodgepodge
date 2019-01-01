package com.mudfish.sharding.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.shardingsphere.api.algorithm.sharding.ListShardingValue;
import io.shardingsphere.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.api.algorithm.sharding.hint.HintShardingAlgorithm;

/**
 * Created by Mudfish on 2018/12/30 0030.
 */
public class OrderHintAlgorithm implements HintShardingAlgorithm {

	@Override
	public Collection<String> doSharding(Collection<String> databaseNames, ShardingValue shardingValue) {
		ListShardingValue<Integer> listShardingValue = (ListShardingValue)shardingValue;
		List<String> result = new ArrayList<String>();
		for (String databaseName : databaseNames) {
			for (Integer value : listShardingValue.getValues()) {
				if (databaseName.endsWith(value % 2 + "")) {
					result.add("dataSource" + value.toString());
				}
			}
		}
		return result;
	}
}
