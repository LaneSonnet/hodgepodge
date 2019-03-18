package com.mudfish.dao.rpc;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mudfish.po.rpc.RpcServer;

public interface RpcServerDao {

	int create(RpcServer rpcServer);

	List<RpcServer> queryByStatus(int status);

	void deleteById(Integer id);

	void delete(@Param("ip") String ip, @Param("rpcPort") int rpcPort);
}
