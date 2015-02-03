package com.cqupt.sysManage.popodom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.util.ObjectCensor;
import com.cqupt.pub.util.XMLCreater;

public class PermissiondomDao {
	private XMLCreater xmlCreater = XMLCreater.getInstance();
	private ObjectCensor objCensor = ObjectCensor.getInstance();
	Logger logger = Logger.getLogger(this.getClass());

	/**
	 * ����Ȩ����
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createPopedomTree() throws Exception {

		String xml = "";
		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();
			String sql = "select menuid,menuname,menu_desc,p_menuid,url,menulevel from qdzc.sys_menu";
			logger.info("PermissiondomDao中的查询语句：" + sql);
			Map dataRecord = session.executeSQL(sql);
			List list = (List) dataRecord.get("resultList");
			List popedomList = new ArrayList();

			for (Iterator it = list.iterator(); it.hasNext();) {
				Map resultMap = (Map) it.next();
				Popedom popedom = new Popedom();
				popedom.setElementDesc(resultMap.get("menuDesc").toString());
				popedom.setElementGrade(resultMap.get("menulevel").toString());
				popedom.setElementId(resultMap.get("menuid").toString());
				popedom.setElementName(resultMap.get("menuname").toString());
				popedom.setPElementId(resultMap.get("pMenuid").toString());
				// logger.info("popedom对象为：" + popedom);
				popedomList.add(popedom);

			}
			logger.info("popedom对象list为：" + popedomList);
			xml = this.createPopedomXml(popedomList);
		} catch (Exception e) {
		}

		return xml;
	}

	/**
	 * ����Ȩ����xml
	 * 
	 * @return
	 */
	public String createPopedomXml(List popedomList) {

		String xml = "";
		Document document = xmlCreater.createDocument();
		Element root = document.getRootElement();

		for (int i = 0; i < popedomList.size(); i++) {
			Popedom firstPopedom = (Popedom) popedomList.get(i);
			String firstElementId = firstPopedom.getElementId();

			String firstElementGrade = firstPopedom.getElementGrade();

			if ("1".equals(firstElementGrade.trim())) {
				Element first = this.xmlCreater.createChildElement(root,
						"first", null);

				this.xmlCreater.createChildElement(first, "name",
						firstPopedom.getElementName());
				this.xmlCreater.createChildElement(first, "id",
						firstPopedom.getElementId());
				for (int ii = 0; ii < popedomList.size(); ii++) {
					Popedom twoPopedom = (Popedom) popedomList.get(ii);
					String twoElementGrade = twoPopedom.getElementGrade();

					if ("2".equals(twoElementGrade.trim())
							&& firstElementId
									.equals(twoPopedom.getPElementId())) {
						String twoElementId = twoPopedom.getElementId();

						Element two = this.xmlCreater.createChildElement(first,
								"second", null);

						this.xmlCreater.createChildElement(two, "name",
								twoPopedom.getElementName());
						this.xmlCreater.createChildElement(two, "id",
								twoPopedom.getElementId());
						for (int iii = 0; iii < popedomList.size(); iii++) {
							Popedom threePopedom = (Popedom) popedomList
									.get(iii);
							String threeElementGrade = threePopedom
									.getElementGrade();

							if ("3".equals(threeElementGrade.trim())
									&& twoElementId.equals(threePopedom
											.getPElementId())) {

								Element three = this.xmlCreater
										.createChildElement(two, "third", null);

								this.xmlCreater.createChildElement(three,
										"name", threePopedom.getElementName());
								this.xmlCreater.createChildElement(three, "id",
										threePopedom.getElementId());
							}
						}
					}
				}
			}

		}
		xml = document.asXML();

		return xml;
	}

	/**
	 * ���ĳһ�ڵ�Id��ȡ�øýڵ���Ϣ
	 * 
	 * @param elementId
	 * @return
	 * @throws DataStormException
	 */
	public List showPopedom(String elementId) throws Exception {

		if (this.objCensor.checkObjectIsNull(elementId)) {
			elementId = "-1";
		}
		DataStormSession session = null;
		List popedomList = new ArrayList();

		try {
			session = DataStormSession.getInstance();
			String sql = "select menuid,menuname,menu_desc,p_menuid,url,menulevel from qdzc.sys_menu where menuid = '"
					+ elementId + "'";
			Map dataMap = session.executeSQL(sql);
			List resultList = (List) dataMap.get("resultList");
			Map resultMap;
			for (Iterator it = resultList.iterator(); it.hasNext();) {
				resultMap = (Map) it.next();
				Popedom popedom = new Popedom();
				popedom.setElementDesc(resultMap.get("menuDesc").toString());
				popedom.setElementGrade(resultMap.get("menulevel").toString());
				popedom.setElementId(resultMap.get("menuid").toString());
				popedom.setElementName(resultMap.get("menuname").toString());
				popedom.setPElementId(resultMap.get("pMenuid").toString());
				popedomList.add(popedom);
			}

		} catch (Exception e) {
		}

		return popedomList;
	}

	/**
	 * ���ĳһ�ڵ�Id��ȡ�øýڵ���Ϣ
	 * 
	 * @param elementId
	 * @return
	 * @throws DataStormException
	 */
	public List showPopedom(List elementId) throws Exception {
		logger.info("elementID为：" + elementId);
		// [{menuid=05}, {menuid=0504}, {menuid=050401}, {menuid=050402},
		// {menuid=050403}, {menuid=050404}, {menuid=06}, {menuid=0605},
		// {menuid=060501}, {menuid=060502}, {menuid=060503}, {menuid=060504},
		// {menuid=060505}, {menuid=07}, {menuid=0704}, {menuid=070401}]
		Map resultMap00 = (Map) elementId.get(0);
		for (int i = 0; i < elementId.size(); i++) {
		}
		logger.info("resultMap00为：" + resultMap00);
		if (this.objCensor.checkObjectIsNull(elementId)) {
			elementId = null;
		}
		DataStormSession session = null;
		List popedomList = new ArrayList();
		String elementIds = "";
		for (int i = 0; i < elementId.size(); i++) {
			elementIds += elementId.get(i) + ",";
		}
		logger.info("elementsIds前:" + elementIds);
		// {menuid=05},{menuid=0504},{menuid=050401},{menuid=050402},{menuid=050403},{menuid=050404},{menuid=06},{menuid=0605},{menuid=060501},{menuid=060502},{menuid=060503},{menuid=060504},{menuid=060505},{menuid=07},{menuid=0704},{menuid=070401},
		elementIds = elementIds.substring(0, elementIds.length() - 1);
		logger.info("elementsIds后:" + elementIds);
		// elementIds = "06,0606,060601,060602,060603,060604";
		try {
			session = DataStormSession.getInstance();
			String sql = "select menuid,menuname,menu_desc,p_menuid,url,menulevel from qdzc.sys_menu where menuid in ("
					+ elementIds + ")";
			Map dataMap = session.executeSQL(sql);
			List resultList = (List) dataMap.get("resultList");
			Map resultMap;
			for (Iterator it = resultList.iterator(); it.hasNext();) {
				resultMap = (Map) it.next();
				Popedom popedom = new Popedom();
				popedom.setElementDesc(resultMap.get("menuDesc").toString());
				popedom.setElementGrade(resultMap.get("menulevel").toString());
				popedom.setElementId(resultMap.get("menuid").toString());
				popedom.setElementName(resultMap.get("menuname").toString());
				popedom.setPElementId(resultMap.get("pMenuid").toString());
				popedomList.add(popedom);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return popedomList;
	}

	/**
	 * ���elementIdȡ��һ��Ȩ��
	 * 
	 * @param elementId
	 * @return
	 * @throws DataStormException
	 */
	public List getPopedomGroup(String elementId) throws Exception {
		List popedomList = this.showPopedom(elementId);

		List popedomGroup = new ArrayList();
		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();
			String sql = "select menuid,menuname,menu_desc,p_menuid,url,menulevel from qdzc.sys_menu";
			Map dataMap = session.executeSQL(sql);
			List resultList = (List) dataMap.get("resultList");
			// adminlogger.info(sql);
			Map resultMap;

			for (Iterator it2 = resultList.iterator(); it2.hasNext();) {
				resultMap = (Map) it2.next();
				Popedom popedom = new Popedom();
				popedom.setElementDesc(resultMap.get("menuDesc").toString());
				popedom.setElementGrade(resultMap.get("menulevel").toString());
				popedom.setElementId(resultMap.get("menuid").toString());
				popedom.setElementName(resultMap.get("menuname").toString());
				popedom.setPElementId(resultMap.get("pMenuid").toString());
				popedomList.add(popedom);
			}

			if (this.objCensor.checkObjectIsNull(elementId)) {
				popedomGroup = this.divPopedomGroup(popedomList);
			} else {
				Popedom popedom = null;
				for (int i = 0; i < popedomList.size(); i++) {
					popedom = (Popedom) popedomList.get(i);
					if (popedom.getElementId().toString().equals(elementId)) {
						break;
					}
				}

				if ("3".equals(popedom.getElementGrade().trim())) {

					popedomGroup = this.getThreeNodeGroup(popedomList, popedom);
				} else if ("2".equals(popedom.getElementGrade().trim())) {

					popedomGroup = this.getTwoNodeGroup(popedomList, popedom);
				} else if ("1".equals(popedom.getElementGrade().trim())) {

					popedomGroup = this.getOneNodeGroup(popedomList, popedom);
				} else {
					throw new Exception("�˵����𲻴��ڣ�");
				}
			}

		} catch (Exception e) {
		}
		return popedomGroup;
	}

	/**
	 * ���roalIdȡ��һ��elementId�õ�Ȩ��
	 * 
	 * @param elementId
	 * @return
	 * @throws DataStormException
	 */
	public List getPopedomGroup(List elementIds) throws Exception {
		logger.info("getPopedomGroup中的List:" + elementIds);
		List popedomList = this.showPopedom(elementIds);
		logger.info("getPopedomGroup中的popedomList:" + popedomList);
		List popedomGroup = new ArrayList();
		DataStormSession session = null;
		List list2 = new ArrayList();
		try {
			session = DataStormSession.getInstance();
			String sql = "select menuid,menuname,menu_desc,p_menuid,url,menulevel from qdzc.sys_menu";
			Map dataMap = session.executeSQL(sql);
			List resultList = (List) dataMap.get("resultList");
			Map resultMap;

			for (Iterator it2 = resultList.iterator(); it2.hasNext();) {
				resultMap = (Map) it2.next();
				Popedom popedom = new Popedom();
				popedom.setElementDesc(resultMap.get("menuDesc").toString());
				popedom.setElementGrade(resultMap.get("menulevel").toString());
				popedom.setElementId(resultMap.get("menuid").toString());
				popedom.setElementName(resultMap.get("menuname").toString());
				popedom.setPElementId(resultMap.get("pMenuid").toString());
				popedomList.add(popedom);
				list2.add(popedom);
			}

			if (this.objCensor.checkObjectIsNull(elementIds)) {
				popedomGroup = this.divPopedomGroup(popedomList);
			} else {
				for (int j = 0; j < elementIds.size(); j++) {
					String elementId = elementIds.get(j).toString();
					logger.info("j:" + j + "  nodeid:" + elementId);
					Popedom popedom = null;
					logger.info("list2():" + list2.size());
					for (int i = 0; i < list2.size(); i++) {

						popedom = (Popedom) list2.get(i);
						if (popedom.getElementId().toString().equals(elementId)) {
							break;
						}
					}

					if ("3".equals(popedom.getElementGrade().trim())) {

						// popedomGroup = this.getThreeNodeGroup(popedomList,
						// popedom);
						Popedom twoPopedom = null;
						// ȡ�ö����ڵ�
						for (int i = 0; i < list2.size(); i++) {
							twoPopedom = (Popedom) list2.get(i);
							String grade = twoPopedom.getElementGrade();
							if ("2".equals(grade.trim())
									&& (twoPopedom.getElementId())
											.equals(popedom.getPElementId())) {
								break;
							}
						}
						if (this.objCensor.checkObjectIsNull(twoPopedom)) {
							throw new Exception("�����ڵ㲻���ڣ�");
						}

						// ȡ��һ���ڵ�
						for (int i = 0; i < list2.size(); i++) {
							Popedom onePopedom = (Popedom) list2.get(i);
							String grade = onePopedom.getElementGrade();

							if ("1".equals(grade.trim())
									&& (onePopedom.getElementId())
											.equals(twoPopedom.getPElementId())) {
								popedomGroup.add(onePopedom);
								break;
							}
						}
						popedomGroup.add(twoPopedom);
						popedomGroup.add(popedom);
					} else if ("2".equals(popedom.getElementGrade().trim())) {

						// popedomGroup = this.getTwoNodeGroup(popedomList,
						// popedom);
						// ȡ��һ���ڵ�
						for (int i = 0; i < list2.size(); i++) {
							Popedom onePopedom = (Popedom) list2.get(i);
							String grade = onePopedom.getElementGrade();
							if ("1".equals(grade.trim())
									&& onePopedom.getElementId().equals(
											popedom.getPElementId())) {
								popedomGroup.add(onePopedom);
							}
						}
						popedomGroup.add(popedom);
					} else if ("1".equals(popedom.getElementGrade().trim())) {

						popedomGroup.add(popedom);
					} else {
						throw new Exception("�˵����𲻴��ڣ�");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("size:" + popedomGroup.size() + "content:"
				+ popedomGroup.toString());
		return popedomGroup;
	}

	/**
	 * ��Ȩ�޷���
	 * 
	 * @param popedomList
	 * @return
	 */
	private List divPopedomGroup(List popedomList) {

		List popedomGroupList = new ArrayList();

		for (int i = 0; i < popedomList.size(); i++) {
			Popedom popedom = (Popedom) popedomList.get(i);
			String grade = popedom.getElementGrade();
			if ("1".equals(grade.trim())) {
				popedomGroupList.addAll(this.getOneNodeGroup(popedomList,
						popedom));
			}
		}

		return popedomGroupList;
	}

	/**
	 * ȡ����ڵ���
	 * 
	 * @param popedomList
	 * @param popedom
	 * @return
	 * @throws DataStormException
	 */
	private List getThreeNodeGroup(List popedomList, Popedom popedom)
			throws Exception {

		List popedomGroup = new ArrayList();
		Popedom twoPopedom = null;
		// ȡ�ö����ڵ�
		for (int i = 0; i < popedomList.size(); i++) {
			twoPopedom = (Popedom) popedomList.get(i);
			String grade = twoPopedom.getElementGrade();
			if ("2".equals(grade.trim())
					&& (twoPopedom.getElementId()).equals(popedom
							.getPElementId())) {
				break;
			}
		}
		if (this.objCensor.checkObjectIsNull(twoPopedom)) {
			throw new Exception("�����ڵ㲻���ڣ�");
		}

		// ȡ��һ���ڵ�
		for (int i = 0; i < popedomList.size(); i++) {
			Popedom onePopedom = (Popedom) popedomList.get(i);
			String grade = onePopedom.getElementGrade();

			if ("1".equals(grade.trim())
					&& (onePopedom.getElementId()).equals(twoPopedom
							.getPElementId())) {
				popedomGroup.add(onePopedom);
				break;
			}
		}
		popedomGroup.add(twoPopedom);
		popedomGroup.add(popedom);
		return popedomGroup;
	}

	/**
	 * ȡ�ö����ڵ���
	 * 
	 * @param popedomList
	 * @param popedom
	 * @return
	 */
	private List getTwoNodeGroup(List popedomList, Popedom popedom) {

		List popedomGroup = new ArrayList();
		// ȡ��һ���ڵ�
		for (int i = 0; i < popedomList.size(); i++) {
			Popedom onePopedom = (Popedom) popedomList.get(i);
			String grade = onePopedom.getElementGrade();
			if ("1".equals(grade.trim())
					&& onePopedom.getElementId()
							.equals(popedom.getPElementId())) {
				popedomGroup.add(onePopedom);
			}
		}
		popedomGroup.add(popedom);
		// ȡ����ڵ�
		for (int i = 0; i < popedomList.size(); i++) {
			Popedom threePopedom = (Popedom) popedomList.get(i);
			String grade = threePopedom.getElementGrade();
			if ("3".equals(grade.trim())
					&& (popedom.getElementId()).equals(threePopedom
							.getPElementId())) {
				popedomGroup.add(threePopedom);
			}
		}

		return popedomGroup;
	}

	/**
	 * ȡ��һ���˵���
	 * 
	 * @param popedomList
	 * @param popedom
	 * @return
	 */
	private List getOneNodeGroup(List popedomList, Popedom popedom) {

		List popedomGroup = new ArrayList();
		popedomGroup.add(popedom);
		// ȡ�ö����ڵ�
		for (int i = 0; i < popedomList.size(); i++) {
			Popedom twoPopedom = (Popedom) popedomList.get(i);
			String twoGrade = twoPopedom.getElementGrade();
			if ("2".equals(twoGrade.trim())
					&& (popedom.getElementId()).equals(twoPopedom
							.getPElementId())) {
				popedomGroup.add(twoPopedom);

				for (int ii = 0; ii < popedomList.size(); ii++) {
					Popedom threePopedom = (Popedom) popedomList.get(ii);
					String threeGrade = threePopedom.getElementGrade();
					if ("3".equals(threeGrade.trim())
							&& (twoPopedom.getElementId()).equals(threePopedom
									.getPElementId())) {
						popedomGroup.add(threePopedom);
					}
				}
			}
		}

		return popedomGroup;
	}

	/**
	 * ���list�е�Ȩ�޷ּ�����
	 * 
	 * @param popedomList
	 * @return
	 */
	public List getPopemdomList(List popedomList) {

		List orderPopemdomList = new ArrayList();
		for (int i = 0; i < popedomList.size(); i++) {
			Popedom plevel1 = (Popedom) popedomList.get(i);
			// װ��һ���ڵ�
			if ("1".equals(plevel1.getElementGrade().trim())) {
				orderPopemdomList.add(plevel1);
				// popedomList.remove(i);
				for (int j = 0; j < popedomList.size(); j++) {
					Popedom plevel2 = (Popedom) popedomList.get(j);
					// װ������ڵ�
					if ("2".equals(plevel2.getElementGrade().trim())
							&& plevel2.getPElementId().trim()
									.equals(plevel1.getElementId().trim())) {
						orderPopemdomList.add(plevel2);
						// popedomList.remove(j);
						for (int k = 0; k < popedomList.size(); k++) {
							Popedom plevel3 = (Popedom) popedomList.get(k);
							// װ����ڵ�
							if ("3".equals(plevel3.getElementGrade().trim())
									&& plevel3
											.getPElementId()
											.trim()
											.equals(plevel2.getElementId()
													.trim())) {
								orderPopemdomList.add(plevel3);
								// popedomList.remove(k);
							}
						}
					}
				}
			}
		}
		return orderPopemdomList;
	}
}
