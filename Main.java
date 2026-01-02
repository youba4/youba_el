    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;
    import java.util.ArrayList;




    public class Main {
        static ArrayList<Borrow> borrows = new ArrayList<>();

    
        static ArrayList<Book> books = new ArrayList<>();
        public static void main(String[] args) {

            JFrame frame1 = new JFrame("Library System");
            
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 80, 10, 80));
            

            JButton buttonBorrowBook = new JButton("Borrow Book");
            JButton buttonSearchBook = new JButton("Search Book");
            JButton buttonAddBook = new JButton("Add Book");
            JButton buttonExit = new JButton("Exit");
        
            buttonBorrowBook.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonBorrowBook.getMinimumSize().height));
            buttonSearchBook.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonSearchBook.getMinimumSize().height));
            buttonAddBook.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonAddBook.getMinimumSize().height));
            buttonExit.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonExit.getMinimumSize().height));
    

            
            panel.add(Box.createVerticalGlue()); 
            panel.add(buttonBorrowBook);
            panel.add(Box.createVerticalStrut(20)); 
            panel.add(buttonSearchBook);
            panel.add(Box.createVerticalStrut(20)); 
            panel.add(buttonAddBook);
            panel.add(Box.createVerticalStrut(20)); 
            panel.add(buttonExit);
            panel.add(Box.createVerticalGlue()); 

            

            frame1.add(panel);

            buttonExit.addActionListener(new ActionListener() {
            
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
    
            buttonBorrowBook.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            frame1.setVisible(false);

            JFrame frame = new JFrame("Borrow Book Page");
            frame.setSize(600, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

            panel.add(new JLabel("Borrow a Book"));
            panel.add(Box.createVerticalStrut(20));

            // إدخال اسم المستعير
            panel.add(new JLabel("First Name:"));
            JTextField firstNameField = new JTextField();
            firstNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            panel.add(firstNameField);
            panel.add(Box.createVerticalStrut(10));

            panel.add(new JLabel("Last Name:"));
            JTextField lastNameField = new JTextField();
            lastNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            panel.add(lastNameField);
            panel.add(Box.createVerticalStrut(10));

            // إدخال ID المستعير
            panel.add(new JLabel("ID:"));
            JTextField idField = new JTextField();
            idField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            panel.add(idField);
            panel.add(Box.createVerticalStrut(20));

            // إدخال الكتاب
            panel.add(new JLabel("Enter Book Title or ISBN:"));
            JTextField borrowField = new JTextField();
            borrowField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            panel.add(borrowField);
            panel.add(Box.createVerticalStrut(20));

            // الأزرار
            JButton borrowButton = new JButton("Borrow");
            borrowButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonBorrowBook.getMinimumSize().height));
            JButton backButton = new JButton("Back");
            backButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonBorrowBook.getMinimumSize().height));
            panel.add(borrowButton);
            panel.add(Box.createVerticalStrut(15));
            panel.add(backButton);

            // زر الاستعارة
            borrowButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    String firstName = firstNameField.getText().trim();
                    String lastName = lastNameField.getText().trim();
                    String idText = idField.getText().trim();
                    String bookInput = borrowField.getText().trim().toLowerCase();
                   

                    if (firstName.isEmpty() || lastName.isEmpty() || idText.isEmpty() || bookInput.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all fields");
                        return;
                    }

                    int id;
                    try {
                        id = Integer.parseInt(idText);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "ID must be a number");
                        return;
                    }

                    boolean found = false;

                   for (Book b : books) {

                       if (b.title.toLowerCase().contains(bookInput)
                          || b.isbn.toLowerCase().contains(bookInput)) {

                       if (b.isBorrowed) {
                         JOptionPane.showMessageDialog(null,
                            "This book is already borrowed!");
                          return;
                        }

                      // استعارة الكتاب
                      b.isBorrowed = true;

                       Borrow br = new Borrow(firstName, lastName, id, b.title);
                       borrows.add(br);

                     JOptionPane.showMessageDialog(null,
                       "Book Borrowed Successfully!\n" +
                       "Title: " + b.title +
                       "\nAuthor: " + b.author +
                       "\nBorrower: " + firstName + " " + lastName +
                       "\nID: " + id);

                      found = true;
                    break;
                           }
                    }


                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Book not found");
                    }
                }
            });
            // زر الرجوع
            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    frame1.setVisible(true);
                }
            });

            frame.add(panel);
            frame.setVisible(true);
            
        }
        });

        buttonAddBook.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            frame1.setVisible(false);

            JFrame frame = new JFrame("Add Book");
            frame.setSize(600, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

            panel.add(new JLabel("Add New Book"));
            panel.add(Box.createVerticalStrut(20));

            // Title
            panel.add(new JLabel("Title:"));
            JTextField titleField = new JTextField();
            titleField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            panel.add(titleField);

            panel.add(Box.createVerticalStrut(10));

            // Author
            panel.add(new JLabel("Author:"));
            JTextField authorField = new JTextField();
            authorField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            panel.add(authorField);

            panel.add(Box.createVerticalStrut(10));

            // ISBN
            panel.add(new JLabel("ISBN:"));
            JTextField isbnField = new JTextField();
            isbnField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            panel.add(isbnField);

            panel.add(Box.createVerticalStrut(10));

            // Year Published
            panel.add(new JLabel("Year Published:"));
            JTextField yearPublishedField = new JTextField();
            yearPublishedField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            panel.add(yearPublishedField);

            panel.add(Box.createVerticalStrut(20));

            JButton saveButton = new JButton("Save Book");
            saveButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            JButton backButton = new JButton("Back");
            backButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

            panel.add(saveButton);
            panel.add(Box.createVerticalStrut(10));
            panel.add(backButton);

            // حفظ الكتاب
            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    String title = titleField.getText();
                    String author = authorField.getText();
                    String isbn = isbnField.getText();
                   


                    if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all fields");
                        return;
                    }
                     int yearPublished;
                     try {
                      yearPublished = Integer.parseInt(yearPublishedField.getText().trim());
                    } catch (NumberFormatException ex) {
                       JOptionPane.showMessageDialog(null, "Year must be a number");
                     return;
              }

                    Book book = new Book(title, author, isbn, yearPublished);
                    books.add(book);

                    JOptionPane.showMessageDialog(null, "Book saved successfully!");

                    titleField.setText("");
                    authorField.setText("");
                    isbnField.setText("");
                }
            });

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    frame1.setVisible(true);
                }
            });

            frame.add(panel);
            frame.setVisible(true);
        }
    });

        buttonSearchBook.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            
            frame1.setVisible(false); 
            JFrame frame = new JFrame("Search Book Page");

            frame.setSize(600, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            // إنشاء JPanel رئيسي للصفحة
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

            // عنوان الصفحة
            panel.add(new JLabel("Welcome to Search Book Page"));
            panel.add(Box.createVerticalStrut(20));

            // خانة البحث بالمؤلف
            panel.add(new JLabel("Search by Author:"));
            JTextField authorField = new JTextField();
            authorField.setMaximumSize(new Dimension(Integer.MAX_VALUE, authorField.getMinimumSize().height));
            panel.add(authorField);
            panel.add(Box.createVerticalStrut(10));

            panel.add(new JLabel("Search by Title:"));
            JTextField titleField = new JTextField();
            titleField.setMaximumSize(new Dimension(Integer.MAX_VALUE, titleField.getMinimumSize().height));
            panel.add(titleField);
            panel.add(Box.createVerticalStrut(20));

        
            JButton searchButton = new JButton("Search");
            searchButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, searchButton.getMinimumSize().height));
            panel.add(searchButton);
            panel.add(Box.createVerticalStrut(20));

            JButton backButton = new JButton("Back");
            backButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, backButton.getMinimumSize().height));
            panel.add(backButton);
            frame.setVisible(true); 

        
            searchButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            String author = authorField.getText().toLowerCase();
            String title  = titleField.getText().toLowerCase();

            boolean found = false;

            for (Book b : books) {
                if (b.author.toLowerCase().contains(author)
                    && b.title.toLowerCase().contains(title)) {

                    JOptionPane.showMessageDialog(null,
                        "Found Book:\n" +
                        "Title: " + b.title + "\n" +
                        "Author: " + b.author + "\n" +
                        "ISBN: " + b.isbn
                    );
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Book not found");
            }
        }
        });


            

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose(); // يغلق نافذة البحث
                    frame1.setVisible(true);
                }
            });

            frame.add(panel);
            
        }
    });

            frame1.setSize(600, 600);
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setLocationRelativeTo(null); 
            frame1.setResizable(false);
            frame1.setVisible(true);
        }
    }
