/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.billing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author OnePiece
 */
public class Bill extends javax.swing.JFrame {

    /**
     * Creates new form Bill
     */
    
    private DBConnect dbc;
    private ResultSet rs;
    String logintype,loginid;
    public Bill(String lgintype,String lginid) {
        initComponents();
        logintype = lgintype;
        loginid = lginid;
        System.out.println(logintype);
        show_table("Select * from resturant.dbo.menu");
        
        update_table("Drop table resturant.dbo.orders;CREATE TABLE resturant.dbo.orders ( id INT NOT NULL PRIMARY KEY,quantity int);");
        show_order("select resturant.dbo.orders.id,resturant.dbo.menu.name,resturant.dbo.menu.price,resturant.dbo.orders.quantity,resturant.dbo.orders.quantity*resturant.dbo.menu.price from resturant.dbo.orders,resturant.dbo.menu where resturant.dbo.orders.id = resturant.dbo.menu.id ");
        
    }

    private Bill() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void closeDB()
    {
        try{
            if (rs != null){rs.close();}
             // if (stmt != null){stmt.close();}
         //if (dbc != null){dbc.close();}
        }catch(Exception e){}
    }
    
    
    int returnvalue(String query){
        
        try{
               
                dbc.connectToDB(); 
                try{
                    java.sql.Statement stmt= dbc.connection.createStatement();
                    rs = stmt.executeQuery(query);
                
                    if(rs.next()){
                        int aa = rs.getInt(1);
                        return aa;
                    }
                    else JOptionPane.showMessageDialog(null, ":(");
                    
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Unable to exicute");
                    Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);  
                }  
                dbc.disconnectFromDB();            
            }catch (SQLException ex) {
                System.out.println(ex.getMessage());   
            }catch (ClassNotFoundException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                closeDB();
            }
            
        
            return -1;
    }
    
    
    
    void insert(String query){
        try{
                dbc = new DBConnect();
                dbc.connectToDB(); 
                
                try{
                    java.sql.Statement stmt= dbc.connection.createStatement();
                    stmt.execute(query);
                
                    
                    JOptionPane.showMessageDialog(null, "New Bill Added");
                        
                    
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Unable to ADD");
                    Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);  
                }  
                dbc.disconnectFromDB();            
            }catch (SQLException ex) {
                System.out.println(ex.getMessage());   
            }catch (ClassNotFoundException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                closeDB();
            }
    }
    
    
    void update_table(String query){
        
            System.out.println(query);
        
            try{
                dbc = new DBConnect();
                dbc.connectToDB(); 
                
                try{
                    java.sql.Statement stmt= dbc.connection.createStatement();
                    stmt.execute(query);
                
                    
                    //JOptionPane.showMessageDialog(null, "Cashier Updated");
                        
                    
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Unable to Update");
                    Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);  
                }  
                dbc.disconnectFromDB();            
            }catch (SQLException ex) {
                System.out.println(ex.getMessage());   
            }catch (ClassNotFoundException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                closeDB();
            }
    }
    
    
    void show_order(String query){
        
        //System.err.println(query);
        
        try{
                dbc = new DBConnect();
                dbc.connectToDB(); 
                try{
                    java.sql.Statement stmt= dbc.connection.createStatement();
                    rs = stmt.executeQuery(query);
                
                    jTable2.setModel(DbUtils.resultSetToTableModel(rs));
                    
                    TableColumnModel tcm = jTable2.getColumnModel();
                    tcm.getColumn(0).setHeaderValue("ID");
                    tcm.getColumn(1).setHeaderValue("Name");
                    tcm.getColumn(2).setHeaderValue("Per Price");
                    tcm.getColumn(3).setHeaderValue("Quantity");
                    tcm.getColumn(4).setHeaderValue("Price");
                    
                  
                    
                    jTable2.setRowHeight(35);
                    
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Unable to exicute");
                    Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);  
                }  
                dbc.disconnectFromDB();            
            }catch (SQLException ex) {
                System.out.println(ex.getMessage());   
            }catch (ClassNotFoundException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                closeDB();
            }
        
        
    }
    
    void show_table(String query){
        
        //String query = "Select * from resturant.dbo.menu"; 
        
        System.err.println(query);
        
        try{
                dbc = new DBConnect();
                dbc.connectToDB(); 
                try{
                    java.sql.Statement stmt= dbc.connection.createStatement();
                    rs = stmt.executeQuery(query);
                
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    
                    TableColumnModel tcm = jTable1.getColumnModel();
                    tcm.getColumn(0).setHeaderValue("Product ID");
                    tcm.getColumn(1).setHeaderValue("Name");
                    tcm.getColumn(2).setHeaderValue("Price");
                    tcm.getColumn(3).setHeaderValue("Type");
                    
                    jTable1.setRowHeight(35);
                    
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Unable to exicute");
                    Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);  
                }  
                dbc.disconnectFromDB();            
            }catch (SQLException ex) {
                System.out.println(ex.getMessage());   
            }catch (ClassNotFoundException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                closeDB();
            }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("LOG OUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Add / Update User");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Add / Update Menu");

        jButton4.setText("Search Employee");

        jButton5.setText("Search Order");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "name", "catagory", "id" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton6.setText("Delete");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Clear");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Sell");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(68, 63, 86));

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setText("0.0");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Total");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("TK");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 60, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1043, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            new login().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setVisible(false);
        this.dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new AddNewUser(logintype).setVisible(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        System.out.println(jTextField1.getText()+ jComboBox1.getSelectedItem().toString());
        
        
        
        show_table("Select * from resturant.dbo.menu where "+ jComboBox1.getSelectedItem().toString()+" LIKE '"+jTextField1.getText()+"%'");
        
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        
        
        int selectedRowIndex = jTable1.getSelectedRow();
        
        int id = Integer.parseInt(jTable1.getModel().getValueAt(selectedRowIndex, 0).toString());
        
        String query = "if exists (select * from resturant.dbo.orders with (updlock,serializable) where id = "+id+") begin update resturant.dbo.orders set quantity = quantity + 1 where id = "+id+" end else begin insert into resturant.dbo.orders values ("+id+",1) end";
        
        System.out.println(query);
        
        update_table(query);
        
        show_order("select resturant.dbo.orders.id,resturant.dbo.menu.name,resturant.dbo.menu.price,resturant.dbo.orders.quantity,resturant.dbo.orders.quantity*resturant.dbo.menu.price from resturant.dbo.orders,resturant.dbo.menu where resturant.dbo.orders.id = resturant.dbo.menu.id ");
        
        int total = 0;
        
        for (int i = 0 ; i < jTable2.getRowCount() ; i++) {
        // null or not Integer will throw exception
            total += (Integer) jTable2.getValueAt(i, 4);
        }
        
        jTextField2.setText(total+"");
        
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        int id = -1;
        try {
            int selectedRowIndex = jTable2.getSelectedRow();
        
            id = Integer.parseInt(jTable2.getModel().getValueAt(selectedRowIndex, 0).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Select first");
        }
        
        System.out.println(id);
        
        String query = "if exists (select * from resturant.dbo.orders with (updlock,serializable) where id = "+id+" and quantity >1)\n" +
                        "begin\n" +
                        "   update resturant.dbo.orders set quantity = quantity - 1 where id = "+id+"\n" +
                        "end\n" +
                        "else\n" +
                        "begin\n" +
                        "   delete from resturant.dbo.orders WHERE id = "+id+";\n" +
                        "end";
            
        update_table(query);
        
        show_order("select resturant.dbo.orders.id,resturant.dbo.menu.name,resturant.dbo.menu.price,resturant.dbo.orders.quantity,resturant.dbo.orders.quantity*resturant.dbo.menu.price from resturant.dbo.orders,resturant.dbo.menu where resturant.dbo.orders.id = resturant.dbo.menu.id ");
        
        int total = 0;
        for (int i = 0 ; i < jTable2.getRowCount() ; i++) {
        // null or not Integer will throw exception
            total += (Integer) jTable2.getValueAt(i, 4);
        }
        
        jTextField2.setText(total+"");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
            update_table("truncate table resturant.dbo.orders;");
            show_order("select resturant.dbo.orders.id,resturant.dbo.menu.name,resturant.dbo.menu.price,resturant.dbo.orders.quantity,resturant.dbo.orders.quantity*resturant.dbo.menu.price from resturant.dbo.orders,resturant.dbo.menu where resturant.dbo.orders.id = resturant.dbo.menu.id ");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
            
            String query = "INSERT INTO resturant.dbo.bill (price,cashiersid) VALUES ("+jTextField2.getText()+","+loginid+")";
            
            System.out.println(query);
            
            insert(query);
            
            query = "SELECT TOP 1 id FROM resturant.dbo.bill ORDER BY ID DESC";
            
            int a = returnvalue(query);
            
            query = "INSERT INTO resturant.dbo.orderdetails ( productid , quantity ) SELECT  id , quantity FROM resturant.dbo.orders ";
            
            insert(query);
            
            System.out.println(a+"");
            
            query = "UPDATE resturant.dbo.orderdetails SET billid = "+a+" WHERE billid IS NULL";
            
            update_table(query);
            
            System.out.println(a+"");
            
            update_table("truncate table resturant.dbo.orders;");
            
            show_order("select resturant.dbo.orders.id,resturant.dbo.menu.name,resturant.dbo.menu.price,resturant.dbo.orders.quantity,resturant.dbo.orders.quantity*resturant.dbo.menu.price from resturant.dbo.orders,resturant.dbo.menu where resturant.dbo.orders.id = resturant.dbo.menu.id ");
            
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new OrderSearch().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
