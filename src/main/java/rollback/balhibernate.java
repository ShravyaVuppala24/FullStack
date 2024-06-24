package rollback;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class balhibernate {
    public static String transferMoney(int senderId, int receiverId, int amount) {
        // Create a Hibernate session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(balance.class)
                .buildSessionFactory();

        Session session = null;
        try {
            session = factory.getCurrentSession();
            // Begin transaction
            session.beginTransaction();

            // Fetch sender and receiver accounts
            balance sender = session.get(balance.class, senderId);
            balance receiver = session.get(balance.class, receiverId);

            // Check if sender has enough balance
            if (sender.getAmount() < amount) {
                return "Insufficient funds";
            }

            // Perform the transfer
            sender.setAmount(sender.getAmount() - amount);
            receiver.setAmount(receiver.getAmount() + amount);

            // Save the changes
            session.update(sender);
            session.update(receiver);

            // Commit transaction
            session.getTransaction().commit();

            return "Transfer successful";
        } catch (Exception ex) {
            ex.printStackTrace();
            // Rollback transaction if there is an error
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            return "Transfer failed";
        } finally {
            // Close the session factory
            factory.close();
        }
    }

    public static void main(String[] args) {
        // Example usage
        String result = transferMoney(2, 1, 1000);
        System.out.println(result);
    }
}


