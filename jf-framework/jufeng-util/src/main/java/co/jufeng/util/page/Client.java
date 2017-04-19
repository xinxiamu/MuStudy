package co.jufeng.util.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Client {
	@SuppressWarnings("null")
	public static PageModel<Admin> findAdmins(int pageNo, int pageSize) {
		Connection conn = null;
		String sql = "select * from admin limit ?,?";
		PageModel<Admin> pageModel = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Admin admin = null;
		List<Admin> list = new ArrayList<Admin>();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, (pageNo - 1) * pageSize);
			pstm.setInt(2, pageNo * pageSize);
			rs = pstm.executeQuery();
			;
			while (rs.next()) {
				admin = new Admin();
				admin.setId(rs.getInt("a_id"));
				admin.setName(rs.getString("a_name"));
				admin.setPassword(rs.getString("a_pwd"));
				list.add(admin);
			}
			ResultSet rs2 = pstm.executeQuery("select count(*) from admin");
			int total = 0;
			if (rs2.next()) {
				total = rs2.getInt(1);
			}
			pageModel = new PageModel<Admin>();
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setTotalRecords(total);
			pageModel.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return pageModel;
	}

	public static void main(String[] args) {//
//		PageModel<Admin> pageModel = Client.findAdmins(2, 4);
		
		List<Admin> lists = Arrays.asList(
				new Admin(1, "张三", "a123456"), 
				new Admin(2, "李四", "a123456"), 
				new Admin(3, "王五", "a123456"),
				new Admin(4, "老六", "a123456"));
		PageModel<Admin> pageModel = new PageModel<Admin>(1, 2, lists, lists.size());
		List<Admin> list = pageModel.getList();
		for (Admin a : list) {
			System.out.print("ID:" + a.getId() + ",用户名:" + a.getName() + ",密码:"
					+ a.getPassword());
			System.out.println();
		}
		System.out.print("当前页:" + pageModel.getPageNo() + " ");
		System.out.print("共" + pageModel.getTotalPages() + "页  ");
		System.out.print("首页:" + pageModel.getTopPageNo() + " ");
		System.out.print("上一页:" + pageModel.getPreviousPageNo() + " ");
		System.out.print("下一页:" + pageModel.getNextPageNo() + " ");
		System.out.print("尾页:" + pageModel.getBottomPageNo() + " ");
		System.out.print("共" + pageModel.getTotalRecords() + "条记录");
		System.out.println();
	}

}