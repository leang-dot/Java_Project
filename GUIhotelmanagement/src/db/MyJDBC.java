package db;

import constants.CommonConstants;

import java.sql.*;

// JDBC - Java Database Connectivity
// this class will be our gateway in accessing our MySQL database
public class MyJDBC {
    // register new user to the database
    // true - register success
    // false - register failure
    public static boolean register(String admin_username, String admin_password){
        try{
            // first check if the username already exists in the database
            if(!checkUser(admin_username)){
                // connect to the database
                Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

                // create insert query
                PreparedStatement insertUser = connection.prepareStatement(
                        "INSERT INTO " + CommonConstants.DB_USERS_TABLE_NAME + "(admin_username, admin_password)" +
                                "VALUES(?, ?)"
                );

                // insert parameters in the insert query
                insertUser.setString(1, admin_username);
                insertUser.setString(2, admin_password);

                // update db with new user
                insertUser.executeUpdate();
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    // check if username already exists in the database
    // false - user doesn't exists
    // true - user exists in the database
    public static boolean checkUser(String admin_username){
        try{
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

            PreparedStatement checkUserExists = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_USERS_TABLE_NAME +
                            " WHERE USERNAME = ?"
            );
            checkUserExists.setString(1, admin_username);

            ResultSet resultSet = checkUserExists.executeQuery();

            // check to see if the result set is empty
            // if it is empty it means that there was no data row that contains the username
            // (i.e user does not exist)
            if(!resultSet.isBeforeFirst()){
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    // validate login credentials by checking to see if username/password pair exists in the database
    public static boolean validateLogin(String admin_username, String admin_password){
        try{
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

            // create select query
            PreparedStatement validateUser = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_USERS_TABLE_NAME +
                            " WHERE USERNAME = ? AND PASSWORD = ?"
            );
            validateUser.setString(1, admin_username);
            validateUser.setString(2, admin_password);

            ResultSet resultSet = validateUser.executeQuery();

            if(!resultSet.isBeforeFirst()){
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return true;
    }
}