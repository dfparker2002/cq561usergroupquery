package com.hpe.cosd.usergroupquery.impl;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;

import com.hpe.cosd.usergroupquery.UserQueryService;

/**
 * One implementation of the {@link UserQueryService}. Note that
 * the repository is injected, not retrieved.
 */
@Service
@Component(metatype = false)
public class UserQueryServiceImpl implements UserQueryService {
    
	private final static String user = "admin";
	private final static String pw = "admin";

    @Reference
    private SlingRepository repository;
    @Reference
    private ResourceResolver rr;
    @Reference
    private ResourceResolverFactory rrf;

    public String getRepositoryName() {
        return repository.getDescriptor(Repository.REP_NAME_DESC);
    }

	public void generateReport(/*String strUname, String strPwd, String StrServerName*/){
		
//		String crxApplicationName = "virtual-crx";

		try{
//			String repoUrl = "//"+StrServerName+":1199/" + crxApplicationName;
//			String workspace = "crx.default";
//			char[] password = strPwd.toCharArray();
	 
//			Session session = rrf.getAdministrativeResourceResolver(null).queryResources(arg0, arg1);
//			QueryManager qm = session.getWorkspace().getQueryManager();
	 
			Session session = rrf.getAdministrativeResourceResolver(null).adaptTo(Session.class);
			String stmt = "//element(*,rep:Group) order by @rep:principalName";
//			rrf.getAdministrativeResourceResolver(null).qqueryResources(arg0, arg1);
//			Query q = qm.createQuery(stmt, Query.XPATH);

			NodeIterator results = q.execute().getNodes();
			Node node = null;
//			ClientNode node = null;
	 
			System.out.println("Groups Query : " + stmt);
			
			int count = 0;
			
			while (results.hasNext()) {
				node = (Node) results.next();

System.out.print(node.getName() + "\n");
				count++;

System.out.print(count);
			}
	 
			stmt = "//element(*,rep:User) order by @rep:principalName";
			q = qm.createQuery(stmt, Query.XPATH);
	 
			results = q.execute().getNodes();


System.out.println("\n\nUsers Query : " + stmt);
	 
			int counter = 0;
			while (results.hasNext()) {
				node = (Node) results.next();
				System.out.print(node.getName() + "-");

				if(node.getProperty("./profile/familyName").getString() != null){
					System.out.print(node.getProperty("./profile/familyName").getString() + " ");
				}
				else{
					System.out.print("No familyName");
				}
				if(node.getName().contains("KMAP") || node.getName().contains("mkrosky")) {
					System.out.print("No givenName");
				}
				else {
				System.out.print(node.getProperty("./profile/givenName").getString() + "\n");
				}
				counter++;
				System.out.print(counter);
			}
			
			session.logout();
		}
		catch(Exception e){
			System.out.println("Error Message is:"+e.getMessage());
		}
	}
}
/*
UserManager userManager = resourceResolver.adaptTo(UserManager.class);
Authorizable ADMIN = userManager.getAuthorizable("administrators");
 */
//public class AemGroupsUsers {
//    public static void main(String[] args) throws Exception {
//        
//		String strUName = "";
//		String strPwd = "";
//		String strServerName = "";
//		String strMessage = "";
//		boolean isValidRequest = true;
//		System.out.println(" Length of Args is"+args.length);
//		if(args != null && args.length > 0 && args.length == 3){
//			if(args[0].length() > 0 && args[0] != "?"){
//				//Configuration File
//				strUName = args[0];
//			}
//			else{
//				strMessage = strMessage + "\n"+"Please Enter a Valid User Name";
//				isValidRequest = false;
//			}
//			if(args[1].length() > 0 && args[1] != "?"){
//				//Configuration File
//				strPwd = args[1];
//			}
//			else{
//				strMessage = strMessage + "\n"+" Please Enter a Valid Password";
//				isValidRequest = false;
//			}
//			if(args[2].length() > 0 && args[2] != "?"){
//				//Configuration File
//				strServerName = args[2];
//			}
//			else{
//				strMessage = "Please Enter a Valid Server Name";
//				isValidRequest = false;
//			}
//		}
//		else{
//			isValidRequest = false;
//			strMessage = "!!Invalid Number of Arguments!!. \nSyntax is: java AemGroupsUsers UserName Password ServerName";
//		}
//        
//		AemGroupsUsers obj = new AemGroupsUsers();
//		if(isValidRequest){
//			strMessage = "Processing Request!!!!!!!!!!!!!";
//			System.out.println(strMessage);
//			obj.generateReport(strUName,strPwd,strServerName);
//		}
//        else{
//			System.out.println(strMessage);
//		}		
//    }