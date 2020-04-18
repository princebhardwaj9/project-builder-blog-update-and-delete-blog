package dao;
/*
1.Create an interface called BlogDaoInterface inside the dao package with the following methods,
void insertBlog(Blog blog)
List selectAllBlogs()
Blog selectBlog(int blogid);
boolean deleteBlog(int id) - boolean updateBlog(Blog blog) 
*/

import java.util.List;

import model.Blog;

public interface BlogDaoInterface {
	
		void insertBlog(Blog blog) throws Exception;
		Blog selectBlog(int blogid) throws Exception;
		List<Blog> selectAllBlogs() throws Exception;
		boolean deleteBlog(int id) throws Exception;
		boolean updateBlog(Blog blog) throws Exception;
	
}
