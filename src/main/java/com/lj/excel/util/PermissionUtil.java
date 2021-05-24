package com.lj.excel.util;

import com.lj.excel.model.AllPermission;
import com.lj.excel.model.SysPermission;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/4/20 16:30
 * @version: V1.0
 */
public class PermissionUtil {
    public static List<AllPermission> getAllPermission() {
        try {
            ClassPathResource cpr = new ClassPathResource("Permission.xml");
            InputStream in = cpr.getInputStream();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            Iterator iterator = root.elementIterator();
            List<AllPermission> list = new ArrayList<>();
            while (iterator.hasNext()) {
                Element mod = (Element) iterator.next();
                List<Attribute> modAttributes = mod.attributes();
                AllPermission ap = new AllPermission();
                for (Attribute att : modAttributes) {
                    if (att.getName().equals("value")) {
                        ap.setModule(att.getValue());
                    }
                    if (att.getName().equals("name")) {
                        ap.setModuleCN(att.getValue());
                    }
                }
                Iterator iteratorChild = mod.elementIterator();
                List<String> permissionList = new ArrayList<>();
                List<String> permissionCNList = new ArrayList<>();
                while (iteratorChild.hasNext()) {
                    Element per = (Element) iteratorChild.next();
                    List<Attribute> perAttributes = per.attributes();
                    for (Attribute att : perAttributes) {
                        if (att.getName().equals("value")) {
                            permissionList.add(att.getValue());
                        }
                        if (att.getName().equals("name")) {
                            permissionCNList.add(att.getValue());
                        }
                    }
                }
                String[] permissionArray = new String[permissionList.size()];
                permissionList.toArray(permissionArray);
                ap.setPermission(permissionArray);
                String[] permissionCNArray = new String[permissionList.size()];
                permissionCNList.toArray(permissionCNArray);
                ap.setPermissionCN(permissionCNArray);
                list.add(ap);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<AllPermission> getAdminOrRolePermission(List<SysPermission> spList) {
        try {
            ClassPathResource cpr = new ClassPathResource("Permission.xml");
            InputStream in = cpr.getInputStream();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            Iterator iterator = root.elementIterator();
            List<AllPermission> list = new ArrayList<>();
            List<AllPermission> apList = new ArrayList<>();
            while (iterator.hasNext()) {
                Element mod = (Element) iterator.next();
                List<Attribute> modAttributes = mod.attributes();
                AllPermission ap = new AllPermission();
                for (Attribute att : modAttributes) {
                    if (att.getName().equals("value")) {
                        ap.setModule(att.getValue());
                    }
                    if (att.getName().equals("name")) {
                        ap.setModuleCN(att.getValue());
                    }
                }
                Iterator iteratorChild = mod.elementIterator();
                List<String> permissionList = new ArrayList<>();
                List<String> permissionCNList = new ArrayList<>();
                while (iteratorChild.hasNext()) {
                    Element per = (Element) iteratorChild.next();
                    List<Attribute> perAttributes = per.attributes();
                    for (Attribute att : perAttributes) {
                        if (att.getName().equals("value")) {
                            permissionList.add(att.getValue());
                        }
                        if (att.getName().equals("name")) {
                            permissionCNList.add(att.getValue());
                        }
                    }
                }
                String[] permissionArray = new String[permissionList.size()];
                permissionList.toArray(permissionArray);
                ap.setPermission(permissionArray);
                String[] permissionCNArray = new String[permissionList.size()];
                permissionCNList.toArray(permissionCNArray);
                ap.setPermissionCN(permissionCNArray);
                list.add(ap);
                for (SysPermission sp : spList) {
                    if (ap.getModule().equals(sp.getModuleCode())) {
                        AllPermission allPermission = new AllPermission();
                        allPermission.setModule(ap.getModule());
                        allPermission.setModuleCN(ap.getModuleCN());
                        allPermission.setPermission(sp.getPermissions());
                        List<String> pcnList = new ArrayList<>();
                        for (int i = 0; i < sp.getPermissions().length; i++) {
                            for (int j = 0; j < ap.getPermission().length; j++) {
                                if (sp.getPermissions()[i].equals(ap.getPermission()[j])) {
                                    pcnList.add(ap.getPermissionCN()[j]);
                                }
                            }
                        }
                        String[] pcnArray = new String[pcnList.size()];
                        pcnList.toArray(pcnArray);
                        allPermission.setPermissionCN(pcnArray);
                        apList.add(allPermission);
                    }
                }
            }
            return apList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
