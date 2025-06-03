import java.util.*;

public class Questions {

    public enum QuestionType {
        ATTACK, DEFENSE
    }

    private List<Question> attackQuestions;
    private List<Question> defenseQuestions;
    private Random random;

    public Questions() {
        attackQuestions = new ArrayList<>();
        defenseQuestions = new ArrayList<>();
        random = new Random();
        loadQuestions();
    }

    private void loadQuestions() {
        // ATTACK SCENARIOS
        attackQuestions.add(new Question(
                "What is a common goal of a spoofing attack?",
                Arrays.asList("To overload a network", "To impersonate another device", "To install malware"),
                1, QuestionType.ATTACK));

        attackQuestions.add(new Question(
                "Which attack intercepts communication between two parties?",
                Arrays.asList("SQL Injection", "Man-in-the-Middle", "DoS"),
                1, QuestionType.ATTACK));

        attackQuestions.add(new Question(
                "What is the main risk of XSS (Cross Site Scripting)?",
                Arrays.asList("Malware installation", "Database deletion", "Stealing session cookies"),
                2, QuestionType.ATTACK));

        attackQuestions.add(new Question(
                "What does SQL injection exploit?",
                Arrays.asList("Database query vulnerabilities", "User password strength", "Firewall weaknesses"),
                0, QuestionType.ATTACK));

        attackQuestions.add(new Question(
                "Which attack aims to make a service unavailable?",
                Arrays.asList("DoS", "XSS", "Phishing"),
                0, QuestionType.ATTACK));

        attackQuestions.add(new Question(
                "What is a goal of social engineering?",
                Arrays.asList("To trick users into revealing confidential info", "To encrypt data", "To break physical locks"),
                0, QuestionType.ATTACK));

        attackQuestions.add(new Question(
                "Which is an example of a physical security flaw?",
                Arrays.asList("Unsecured server room", "Weak password", "Lack of antivirus"),
                0, QuestionType.ATTACK));

        attackQuestions.add(new Question(
                "What does poor configuration often result in?",
                Arrays.asList("Unauthorized access", "Slow systems", "Better efficiency"),
                0, QuestionType.ATTACK));

        attackQuestions.add(new Question(
                "What leads to successful phishing attacks?",
                Arrays.asList("Strong encryption", "Lack of user awareness", "Firewalls"),
                1, QuestionType.ATTACK));

        attackQuestions.add(new Question(
                "What makes public WiFi dangerous?",
                Arrays.asList("Encryption", "Insecure connections", "Strong passwords"),
                1, QuestionType.ATTACK));

        attackQuestions.add(new Question(
                "What is a zero-day exploit?",
                Arrays.asList("An attack using old vulnerabilities", "A known malware", "A newly discovered, unpatched flaw"),
                2, QuestionType.ATTACK));

        // DEFENSE SCENARIOS
        defenseQuestions.add(new Question(
                "What is the purpose of secure configuration?",
                Arrays.asList("To improve speed", "To reduce vulnerabilities", "To store passwords"),
                1, QuestionType.DEFENSE));

        defenseQuestions.add(new Question(
                "Why is access control important?",
                Arrays.asList("To manage user permissions", "To prevent data backups", "To increase software performance"),
                0, QuestionType.DEFENSE));

        defenseQuestions.add(new Question(
                "How does malware protection help?",
                Arrays.asList("It speeds up your PC", "It blocks unauthorized access", "It detects and removes malicious software"),
                2, QuestionType.DEFENSE));

        defenseQuestions.add(new Question(
                "What does security training focus on?",
                Arrays.asList("Teaching users to identify threats", "Installing firewalls", "Encrypting databases"),
                0, QuestionType.DEFENSE));

        defenseQuestions.add(new Question(
                "How can building security enhance cybersecurity?",
                Arrays.asList("It lowers electricity bills", "It prevents physical tampering", "It speeds up internet"),
                1, QuestionType.DEFENSE));

        defenseQuestions.add(new Question(
                "What does network monitoring detect?",
                Arrays.asList("Abnormal activity", "New software", "Physical security flaws"),
                0, QuestionType.DEFENSE));

        defenseQuestions.add(new Question(
                "What is the role of security policies?",
                Arrays.asList("To set rules and best practices", "To boost CPU power", "To install updates"),
                0, QuestionType.DEFENSE));

        defenseQuestions.add(new Question(
                "How do firewalls help?",
                Arrays.asList("By creating strong passwords", "By controlling incoming/outgoing traffic", "By deleting malware"),
                1, QuestionType.DEFENSE));

        defenseQuestions.add(new Question(
                "Why is data backup critical?",
                Arrays.asList("To create duplicate data", "To recover from data loss", "To avoid using cloud storage"),
                1, QuestionType.DEFENSE));
    }

    public Question getRandomQuestion(QuestionType type) {
        List<Question> list = type == QuestionType.ATTACK ? attackQuestions : defenseQuestions;
        return list.get(random.nextInt(list.size()));
    }

}