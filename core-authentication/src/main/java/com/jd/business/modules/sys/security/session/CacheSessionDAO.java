package com.jd.business.modules.sys.security.session;

import common.base.Servlets;
import common.config.Global;
import common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 系统安全认证实现类
 * @author ThinkGem
 * @version 2014-7-24
 */
@Slf4j
public class CacheSessionDAO extends EnterpriseCacheSessionDAO {

    public CacheSessionDAO() {
        super();
    }

    @Override
    protected void doUpdate(Session session) {
    	if (session == null || session.getId() == null) {  
            return;
        }
    	
    	HttpServletRequest request = Servlets.getRequest();
		if (request != null){
			String uri = request.getServletPath();
			// 如果是静态文件，则不更新SESSION
			if (Servlets.isStaticFile(uri)){
				return;
			}
			// 如果是视图文件，则不更新SESSION
			if (StringUtils.startsWith(uri, Global.getConfig("web.view.prefix"))
					&& StringUtils.endsWith(uri, Global.getConfig("web.view.suffix"))){
				return;
			}
			// 手动控制不更新SESSION
			String updateSession = request.getParameter("updateSession");
			if (Global.FALSE.equals(updateSession) || Global.NO.equals(updateSession)){
				return;
			}
		}
    	super.doUpdate(session);
    	log.debug("update {} {}", session.getId(), request != null ? request.getRequestURI() : "");
    }

    @Override
    protected void doDelete(Session session) {
    	if (session == null || session.getId() == null) {  
            return;
        }
    	
    	super.doDelete(session);
    	log.debug("delete {} ", session.getId());
    }

    @Override
    protected Serializable doCreate(Session session) {
		HttpServletRequest request = Servlets.getRequest();
		if (request != null){
			String uri = request.getServletPath();
			// 如果是静态文件，则不创建SESSION
			if (Servlets.isStaticFile(uri)){
		        return null;
			}
		}
		super.doCreate(session);
		log.debug("doCreate {} {}", session, request != null ? request.getRequestURI() : "");
    	return session.getId();
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
		return super.doReadSession(sessionId);
    }
    
    @Override
    public Session readSession(Serializable sessionId) throws UnknownSessionException {
    	try{
    		Session s = null;
    		HttpServletRequest request = Servlets.getRequest();
    		if (request != null){
    			String uri = request.getServletPath();
    			// 如果是静态文件，则不获取SESSION
    			if (Servlets.isStaticFile(uri)){
    				return null;
    			}
    			s = (Session)request.getAttribute("session_"+sessionId);
    		}
    		if (s != null){
    			return s;
    		}

    		Session session = super.readSession(sessionId);
    		log.debug("readSession {} {}", sessionId, request != null ? request.getRequestURI() : "");
    		
    		if (request != null && session != null){
    			request.setAttribute("session_"+sessionId, session);
    		}
    		
    		return session;
    	}catch (UnknownSessionException e) {
			return null;
		}
    }

}
