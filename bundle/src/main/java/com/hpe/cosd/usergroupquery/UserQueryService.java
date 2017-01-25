package com.hpe.cosd.usergroupquery;

/**
 * A simple service interface
 */
public interface UserQueryService {
    
    /**
     * @return the name of the underlying JCR repository implementation
     */
    public String getRepositoryName();

    public String generateReport();

}