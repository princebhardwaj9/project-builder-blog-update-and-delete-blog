package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Blog;
import utility.ConnectionManager;

/*
1.Create a dao-class called BlogDaoImpl inside the dao package.
2.It should implements the BlogDaoInterface.
3.Implement the following methods,
void insertBlog(Blog blog) - method to insert the blog into the database.
List selectAllBlogs() - method to retrieve the blogs from the database.
Blog selectBlog(int blogid) - method to retrieve a selected blog based on its id.
boolean deleteBlog(int id) - method to delete a selected blog. - boolean updateBlog(Blog blog) - method to update a blog.
*/

public class BlogDaoImpl implements BlogDaoInterface {
	List<Blog> selectAllBlogs=new ArrayList<Blog>();
	Connection con;

	@Override
	public void insertBlog(Blog blog) throws Exception {
		con=ConnectionManager.getConnection();
		PreparedStatement ps=con.prepareStatement("INSERT INTO blog VALUES (?,?,?,?)");
		ps.setInt(1, blog.getBlogId());
		ps.setString(2, blog.getBlogTitle());
		ps.setString(3, blog.getBlogDescription());
		ps.setString(4, blog.getPostedOn());
		ps.execute();
		con.close();
	}

	@Override
	public Blog selectBlog(int blogid) throws Exception {
		Blog blog=new Blog();
		con=ConnectionManager.getConnection();
		PreparedStatement ps=con.prepareStatement("SELECT * FROM blog WHERE id=?");
		ps.setInt(1, blogid);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			blog.setBlogId(rs.getInt(1));
			blog.setBlogTitle(rs.getString(2));
			blog.setBlogDescription(rs.getString(3));
			blog.setPostedOn(rs.getString(4));
		}
		return blog;
	}

	@Override
	public List<Blog> selectAllBlogs() throws Exception {
		con=ConnectionManager.getConnection();
		PreparedStatement ps=con.prepareStatement("SELECT * FROM blog");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Blog blog=new Blog();
			blog.setBlogId(rs.getInt(1));
			blog.setBlogTitle(rs.getString(2));
			blog.setBlogDescription(rs.getString(3));
			blog.setPostedOn(rs.getString(4));
			selectAllBlogs.add(blog);
		}
		return selectAllBlogs;
	}

	@Override
	public boolean deleteBlog(int id) throws Exception {
		con=ConnectionManager.getConnection();
		PreparedStatement ps1=con.prepareStatement("SELECT * FROM blog WHERE id=?");
		ps1.setInt(1, id);
		ResultSet rs=ps1.executeQuery();
		if(rs.next()) {
			PreparedStatement ps2=con.prepareStatement("DELETE FROM blog WHERE id=?");
			ps2.setInt(1, id);
			ps2.execute();
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean updateBlog(Blog blog) throws Exception {
		con=ConnectionManager.getConnection();
		PreparedStatement ps=con.prepareStatement("UPDATE blog SET title=?,blogdesc=?,postedon=? WHERE id=?");
		ps.setString(1, blog.getBlogTitle());
		ps.setString(1, blog.getBlogDescription());
		ps.setString(1, blog.getPostedOn());
		ps.setInt(1, blog.getBlogId());
		ps.execute();
		return true;
	}
	
}