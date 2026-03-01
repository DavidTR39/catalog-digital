import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean powerOn = true;
        Scanner usrInput = new Scanner(System.in);
        Student[] listaStudenti = new Student[100];
        Note[] listaNote = new Note[100];
        Discipline[] listaDisc = new Discipline[6];
        int counterStudent = -1;
        int indGrades = -1;
        // de schimbat path-ul...
        //---------------------
        File db = new File("E:\\JavaProjects\\Project2\\src\\db.txt");
        File dbGrade = new File("E:\\JavaProjects\\Project2\\src\\dbnote.txt");
        File dbDisc = new File("E:\\JavaProjects\\Project2\\src\\dbdiscipline");
        //---------------------
        String line;
        
        //initializare
        try {
            BufferedReader in = new BufferedReader(new FileReader(db));
            
            for (int i = 0; i <= 100; i++) {
                if ((line = in.readLine()) != null) {
                    counterStudent++;
                    listaStudenti[i] = new Student();
                    String[] studentProcessing = line.split(", ");
                    for (int j = 0; j < listaStudenti[i].id.length; j++) {
                        listaStudenti[i].id[j] = Character.getNumericValue(studentProcessing[0].charAt(j));
                    }
                    listaStudenti[i].nume = studentProcessing[1];
                    listaStudenti[i].prenume = studentProcessing[2];
                    listaStudenti[i].email = studentProcessing[3];
                    listaStudenti[i].grupa = studentProcessing[4];
                } else {
                    break;
                    
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        
        try {
            BufferedReader in = new BufferedReader(new FileReader(dbGrade));
            
            for (int i = 0; i <= 100; i++) {
                if ((line = in.readLine()) != null) {
                    indGrades++;
                    listaNote[i] = new Note();
                    String[] gradeProcessing = line.split(", ");
                    for (int j = 0; j < listaNote[i].id.length; j++) {
                        listaNote[i].id[j] = Character.getNumericValue(gradeProcessing[0].charAt(j));
                    }
                    for (int j = 0; j < listaNote[i].student_id.length; j++) {
                        listaNote[i].student_id[j] = Character.getNumericValue(gradeProcessing[1].charAt(j));
                    }
                    for (int j = 0; j < listaNote[i].disciplina_id.length; j++) {
                        listaNote[i].disciplina_id[j] = Character.getNumericValue(gradeProcessing[2].charAt(j));
                    }
                    listaNote[i].nota = Integer.parseInt(gradeProcessing[3]);
                    listaNote[i].data_notarii = LocalDate.parse(gradeProcessing[4]);
                } else {
                    break;
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        
        try {
            BufferedReader in = new BufferedReader(new FileReader(dbDisc));
            
            for (int i = 0; i < 6; i++) {
                if ((line = in.readLine()) != null) {
                    listaDisc[i] = new Discipline();
                    String[] discProcessing = line.split(", ");
                    for (int j = 0; j < listaDisc[i].id.length; j++) {
                        listaDisc[i].id[j] = Character.getNumericValue(discProcessing[0].charAt(j));
                    }
                    listaDisc[i].nume = discProcessing[1];
                    listaDisc[i].acronim = discProcessing[2];
                    listaDisc[i].tip_evaluare = discProcessing[3];
                } else {
                    break;
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        
        //System.out.println(indGrades);
        
        
        
        do {
            System.out.println("Ce vreti sa faceti?");
            System.out.println("a) Creeati un student nou");
            System.out.println("b) Cititi notele unui student");
            System.out.println("c) Actualizati notele unui student");
            System.out.println("d) Stergeti un student din baza de date");
            System.out.println("e) Shut down");
            
            char choice = Character.toLowerCase(usrInput.nextLine().charAt(0));
            
            if (choice == 'a') {
                counterStudent++;
                listaStudenti[counterStudent] = new Student();
                System.out.print("Numele: ");
                listaStudenti[counterStudent].nume = usrInput.nextLine();
                Character.toUpperCase(listaStudenti[counterStudent].nume.charAt(0));
                System.out.print("Prenumele: ");
                listaStudenti[counterStudent].prenume = usrInput.nextLine();
                Character.toUpperCase(listaStudenti[counterStudent].nume.charAt(0));
                System.out.print("Email: ");
                listaStudenti[counterStudent].email = usrInput.nextLine();
                
                while (!listaStudenti[counterStudent].email.contains("@student.unitbv.ro")) {
                    System.out.print("Emailul nu este valid! Mai inercati iar: ");
                    listaStudenti[counterStudent].email = usrInput.nextLine();
                }
                
                System.out.print("Grupa: ");
                listaStudenti[counterStudent].grupa = usrInput.nextLine();
                
                for (int i = 0; i < listaStudenti[counterStudent].id.length; i++) {
                    listaStudenti[counterStudent].id[i] = (int)(Math.random() * 9);
                }
                
                System.out.print("ID-ul studentului este: ");
                for (int i = 0; i < listaStudenti[counterStudent].id.length; i++) {
                    System.out.print(listaStudenti[counterStudent].id[i]);
                }
                System.out.println("\nOperatie finalizata cu success...");
            }
            
            if (choice == 'b') {
                    System.out.println("Alegeti studentul: ");
                    boolean isValid = false;
                do {
                    try {
                        BufferedReader in = new BufferedReader(new FileReader(db));
                        
                        for (int i = 0; i <= counterStudent; i++) {
                            if ((line = in.readLine()) != null) {
                                String[] studentProcessing = line.split(", ");
                                
                                if (i == counterStudent) {
                                    System.out.println(studentProcessing[1] + ": ");
                                    break;
                                }
                                
                                System.out.print(studentProcessing[1] + ", ");
                                
                            }
                        }
                        in.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                    
                    String seeStudent = usrInput.nextLine();
                    //String cache = editStudent;
                    if (!seeStudent.isEmpty()) {
                        seeStudent = Character.toUpperCase(seeStudent.charAt(0)) + seeStudent.substring(1).toLowerCase();
                    }
                    
                    try {
                        BufferedReader in = new BufferedReader(new FileReader(db));
                        
                        for (int i = 0; i <= counterStudent; i++) {
                            if ((line = in.readLine()) != null) {
                                String[] studentProcessing = line.split(", ");
                                if (seeStudent.equals(studentProcessing[1])) {
                                    System.out.print("ID: ");
                                    for (int j = 0; j < listaStudenti[i].id.length; j++) {
                                        if (j == listaStudenti[i].id.length - 1) {
                                            System.out.println(listaStudenti[i].id[j]);
                                            break;
                                        }
                                        System.out.print(listaStudenti[i].id[j]);
                                    }
                                    System.out.println("Nume: " + listaStudenti[i].nume);
                                    System.out.println("Prenume: " + listaStudenti[i].prenume);
                                    System.out.println("Email: " + listaStudenti[i].email);
                                    System.out.println("Grupa: " + listaStudenti[i].grupa);
                                    System.out.println("--------------------------");
                                    
                                    for (int j = 0; j <= indGrades; j++) {
                                        if (indGrades == -1) {
                                            System.out.println("Studentul nu are note.");
                                        break;
                                        }
                                        if (Arrays.equals(listaStudenti[i].id, listaNote[j].student_id)) {
                                            System.out.print("ID: ");
                                            for (int k = 0; k < listaNote[j].id.length; k++) {
                                                if (k == listaNote[j].id.length - 1) {
                                                    System.out.println(listaNote[j].id[k]);
                                                    break;
                                                }
                                                System.out.print(listaNote[j].id[k]);
                                            }
                                            System.out.print("ID Student: ");
                                            for (int k = 0; k < listaNote[j].student_id.length; k++) {
                                                if (k == listaNote[j].student_id.length - 1) {
                                                    System.out.println(listaNote[j].student_id[k]);
                                                    break;
                                                }
                                                System.out.print(listaNote[j].student_id[k]);
                                            }
                                            System.out.print("ID Disciplina: ");
                                            for (int k = 0; k < listaNote[j].disciplina_id.length; k++) {
                                                if (k == listaNote[j].disciplina_id.length - 1) {
                                                    System.out.println(listaNote[j].disciplina_id[k]);
                                                    break;
                                                }
                                                System.out.print(listaNote[j].disciplina_id[k]);
                                            }
                                            System.out.println("Nota: " + listaNote[j].nota);
                                            System.out.println("Data Notarii: " + listaNote[j].data_notarii);
                                            System.out.println("-----------------------------------------");
                                        }
                                    }
                                    
                                    isValid = true;
                                    break;
                                }
                            }
                        }
                        in.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                    
                    if (isValid == false) {
                        System.out.println("Studentul nu a fost gasit, incercati iar: ");
                    }
                } while (isValid == false);
            }
            
            if (choice == 'c') {
                System.out.println("Alegeti studentul: ");
                boolean isValid = false;
                boolean hasGrades = false;
                try {
                    BufferedReader in = new BufferedReader(new FileReader(db));
                    
                    for (int i = 0; i <= counterStudent; i++) {
                        if ((line = in.readLine()) != null) {
                            String[] studentProcessing = line.split(", ");
                            
                            if (i == counterStudent) {
                                System.out.println(studentProcessing[1] + ": ");
                                break;
                            }
                            
                            System.out.print(studentProcessing[1] + ", ");
                            
                        }
                    }
                    in.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
                String editStudent = usrInput.nextLine();
                if (!editStudent.isEmpty()) {
                    editStudent = Character.toUpperCase(editStudent.charAt(0)) + editStudent.substring(1).toLowerCase();
                }
                
                try {
                    BufferedReader in = new BufferedReader(new FileReader(db));
                    
                    for (int i = 0; i <= counterStudent; i++) {
                        if ((line = in.readLine()) != null) {
                            String[] studentProcessing = line.split(", ");
                            if (editStudent.equals(studentProcessing[1])) {
                                System.out.print("ID: ");
                                    for (int j = 0; j < listaStudenti[i].id.length; j++) {
                                        if (j == listaStudenti[i].id.length - 1) {
                                            System.out.println(listaStudenti[i].id[j]);
                                            break;
                                        }
                                        System.out.print(listaStudenti[i].id[j]);
                                    }
                                System.out.println("Nume: " + listaStudenti[i].nume);
                                System.out.println("Prenume: " + listaStudenti[i].prenume);
                                System.out.println("Grupa: " + listaStudenti[i].grupa);
                                
                                for (int j = 0; j <= indGrades; j++) {
                                    if (indGrades == -1) {
                                        break;
                                    }
                                    if (Arrays.equals(listaStudenti[i].id, listaNote[j].student_id)) {
                                        System.out.println("-----------------------------------");
                                        System.out.print("ID: ");
                                        for (int k = 0; k < listaNote[i].id.length; k++) {
                                            if (k == listaNote[i].id.length - 1) {
                                                System.out.println(listaNote[i].id[k]);
                                                break;
                                            }
                                            System.out.print(listaNote[i].id[k]);
                                        }
                                        System.out.print("ID Student: ");
                                        for (int k = 0; k < listaNote[i].student_id.length; k++) {
                                            if (k == listaNote[i].student_id.length - 1) {
                                                System.out.println(listaNote[i].student_id[k]);
                                                break;
                                            }
                                            System.out.print(listaNote[i].student_id[k]);
                                        }
                                        System.out.print("ID Disciplina: ");
                                        for (int k = 0; k < listaNote[i].disciplina_id.length; k++) {
                                            if (k == listaNote[i].disciplina_id.length - 1) {
                                                System.out.println(listaNote[i].disciplina_id[k]);
                                                break;
                                            }
                                            System.out.print(listaNote[i].disciplina_id[k]);
                                        }
                                        System.out.println("Nota: " + listaNote[j].nota);
                                        System.out.println("Data Notarii: " + listaNote[j].data_notarii);
                                        hasGrades = true;
                                    }
                                    
                                }
                                if (hasGrades == false) {
                                    System.out.print("Studentul nu are nicio nota. Doriti sa-i adaugati o nota? (Y/N): ");
                                    if (Character.toUpperCase(usrInput.nextLine().charAt(0)) == 'Y') {
                                        System.out.print("Alegeti ID-ul disciplinei:");
                                        String reqDisc = usrInput.nextLine();
                                        while (true) {
                                            if (reqDisc.length() > 6) {
                                                System.out.println("ID invalid, incercati iar...");
                                                reqDisc = usrInput.nextLine();
                                            } else {
                                                break;
                                            }
                                        }
                                        int requestId[] = new int[6];
                                        for (int j = 0; j < requestId.length; j++) {
                                            requestId[j] = Character.getNumericValue(reqDisc.charAt(j));
                                        }
                                        System.out.print("Nota: ");
                                        int reqGrade = usrInput.nextInt();
                                        usrInput.nextLine();
                                        indGrades++;
                                        listaNote[indGrades] = new Note();
                                        for (int j = 0; j < listaNote[indGrades].id.length; j++) {
                                            listaNote[indGrades].id[j] = (int)(Math.random() * 9);
                                        }
                                        for (int j = 0; j < listaDisc.length; j++) {
                                            if (Arrays.equals(requestId, listaDisc[j].id)) {
                                                listaNote[indGrades].disciplina_id = requestId;
                                            }
                                        }
                                        listaNote[indGrades].student_id = listaStudenti[i].id;
                                        listaNote[indGrades].nota = reqGrade;
                                        listaNote[indGrades].data_notarii = LocalDate.now();
                                        
                                        System.out.println("Nota a fost adaugata cu succes!");
                                        isValid = true;
                                        break;
                                        
                                    }
                                }
                                System.out.print("Vreti sa adaugati o nota noua? (Y/N): " );
                                if (Character.toUpperCase(usrInput.nextLine().charAt(0)) == 'Y') {
                                    System.out.print("Alegeti ID-ul disciplinei:");
                                    String reqDisc = usrInput.nextLine();
                                    while (true) {
                                        if (reqDisc.length() > 6) {
                                            System.out.println("ID invalid, incercati iar...");
                                            reqDisc = usrInput.nextLine();
                                        } else {
                                            break;
                                        }
                                    }
                                    System.out.println(reqDisc);
                                    int requestId[] = new int[6];
                                    for (int j = 0; j < requestId.length; j++) {
                                        requestId[j] = Character.getNumericValue(reqDisc.charAt(j));
                                    }
                                    System.out.print("Nota: ");
                                    int reqGrade = usrInput.nextInt();
                                    usrInput.nextLine();
                                    indGrades++;
                                    listaNote[indGrades] = new Note();
                                    for (int j = 0; j < listaNote[indGrades].id.length; j++) {
                                        listaNote[indGrades].id[j] = (int)(Math.random() * 9);
                                    }
                                    for (int j = 0; j < listaDisc.length; j++) {
                                        if (Arrays.equals(requestId, listaDisc[j].id)) {
                                            listaNote[indGrades].disciplina_id = requestId;
                                        }
                                    }
                                    
                                    listaNote[indGrades].student_id = listaStudenti[i].id;
                                    listaNote[indGrades].nota = reqGrade;
                                    listaNote[indGrades].data_notarii = LocalDate.now();
                                    
                                    break;
                                }
                                
                                System.out.println("Alegeti id-ul notei: ");
                                String cache = usrInput.nextLine();
                                while (true) {
                                    if (cache.length() > 6) {
                                        System.out.println("ID invalid, incercati iar...");
                                        cache = usrInput.nextLine();
                                    }
                                    else {
                                        break;
                                    }
                                }
                                
                                int requestId[] = new int[6];
                                for (int j = 0; j < requestId.length; j++) {
                                    requestId[j] = Character.getNumericValue(cache.charAt(j));
                                }
                                System.out.println("Nota: ");
                                int requestGrade = usrInput.nextInt();
                                usrInput.nextLine();
                                
                                while (true) {
                                    if (requestGrade < 0 || requestGrade > 10) {
                                        System.out.println("Nota invalida! Incercati iar...");
                                        requestGrade = usrInput.nextInt();
                                    } else {
                                        break;
                                    }
                                }
                                
                                for (int j = 0; j < listaDisc.length; j++) {
                                    if (Arrays.equals(requestId, listaDisc[j].id)) {
                                        listaNote[indGrades].disciplina_id = requestId;
                                    }
                                }
                                    
                                listaNote[indGrades].student_id = listaStudenti[i].id;
                                listaNote[indGrades].nota = requestGrade;
                                
                                isValid = true;
                                break;
                                
                                
                            }
                        }
                        
                    }
                    in.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
                if(isValid) {
                    System.out.println("\nOperatie finalizata cu success...");  
                }
            }
            if (choice == 'd') {
                System.out.println("Alegeti studentul: ");
                boolean isValid = false;
                
                try {
                    BufferedReader in = new BufferedReader(new FileReader(db));
                    
                    for (int i = 0; i <= counterStudent; i++) {
                        if ((line = in.readLine()) != null) {
                            String[] studentProcessing = line.split(", ");
                            
                            if (i == counterStudent) {
                                System.out.println(studentProcessing[1] + ": ");
                                break;
                            }
                            
                            System.out.print(studentProcessing[1] + ", ");
                            
                        }
                    }
                    in.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
                String deleteStudent = usrInput.nextLine();
                if (!deleteStudent.isEmpty()) {
                    deleteStudent = Character.toUpperCase(deleteStudent.charAt(0)) + deleteStudent.substring(1).toLowerCase();
                }
                try {
                    BufferedReader in = new BufferedReader(new FileReader(db));
                    
                    for (int i = 0; i <= counterStudent; i++) {
                        if ((line = in.readLine()) != null) {
                            String[] studentProcessing = line.split(", ");
                            if (deleteStudent.equals(studentProcessing[1])) {
                                System.out.println("Studentul pe care vreti sa-l stergeti din baza de date are urmatoarele date: ");
                                System.out.print("ID: ");
                                for (int j = 0; j < listaStudenti[i].id.length; j++) {
                                    if (j == listaStudenti[i].id.length - 1) {
                                        System.out.println(listaStudenti[i].id[j]);
                                        break;
                                    }
                                    System.out.print(listaStudenti[i].id[j]);
                                }
                                System.out.println("Nume: " + listaStudenti[i].nume);
                                System.out.println("Prenume: " + listaStudenti[i].prenume);
                                System.out.println("Grupa: " + listaStudenti[i].grupa);
                                
                                System.out.print("Doriti sa confirmati? (Y/N): ");
                                char confirm = Character.toUpperCase(usrInput.nextLine().charAt(0));
                                
                                if (confirm == 'Y') {
                                    int[] deletedStudentId = listaStudenti[i].id.clone();
                                    java.util.List<String> linesGr = new java.util.ArrayList<>();
                                    try (BufferedReader reader = new BufferedReader(new FileReader(dbGrade))) {
                                        String currentLine;
                                        while ((currentLine = reader.readLine()) != null) {
                                            String[] gradeProcessing = currentLine.split(", ");
                                            int[] gradeStudentId = new int[deletedStudentId.length];
                                            for (int j = 0; j < gradeStudentId.length; j++) {
                                                gradeStudentId[j] = Character.getNumericValue(gradeProcessing[1].charAt(j));
                                            }
                                            
                                            if (!Arrays.equals(gradeStudentId, deletedStudentId)) {
                                                linesGr.add(currentLine);
                                            }
                                        }
                                        
                                    } catch (IOException e) {
                                        System.out.println(e);
                                    }
                                        
                                        
                                    try (PrintWriter out = new PrintWriter(new FileOutputStream(dbGrade))) {
                                        for (String l : linesGr) {
                                            out.println(l);
                                        }
                                    } catch (IOException e) {
                                        System.out.println(e);
                                    }
                                    
                                    int j = 0;
                                    while (j <= indGrades) {
                                        if (Arrays.equals(listaNote[j].student_id, deletedStudentId)) {
                                            // Shift left to remove the note
                                            for (int k = j; k < indGrades; k++) {
                                                listaNote[k] = listaNote[k + 1];
                                            }
                                            listaNote[indGrades] = null;
                                            indGrades--;
                                            // Do not increment j, check the new element at this index
                                        } else {
                                            j++;
                                        }
                                    }
                                        
                                    java.util.List<String> lines = new java.util.ArrayList<>();
                                    try (BufferedReader reader = new BufferedReader(new FileReader(db))) {
                                        String currentLine;
                                        while ((currentLine = reader.readLine()) != null) {
                                            studentProcessing = currentLine.split(", ");
                                            
                                            if (!deleteStudent.equals(studentProcessing[1])) {
                                                lines.add(currentLine);
                                            }
                                        }
                                    } catch (IOException e) {
                                        System.out.println(e);
                                    }
                                    
                                    try (PrintWriter out = new PrintWriter(new FileOutputStream(db))) {
                                        for (String l : lines) {
                                            out.println(l);
                                        }
                                    } catch (IOException e) {
                                        System.out.println(e);
                                    }
                                    
                                        
                                    for (int k = i; k < counterStudent; k++) {
                                        listaStudenti[k] = listaStudenti[k + 1];
                                    }
                                    listaStudenti[counterStudent] = null;
                                    counterStudent--;
                                }
                                isValid = true;
                                break;
                            }
                        }
                    }
                    if (isValid == true) {
                        System.out.println("Noua lista: ");
                        for (int i = 0; i <= counterStudent; i++) {
                            if (i == counterStudent) {
                                System.out.println(listaStudenti[i].nume);
                                break;
                            }
                            System.out.print(listaStudenti[i].nume + ", ");
                        }
                        System.out.println("\nOperatie finalizata cu success...");
                    }
                    
                    in.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            if (choice == 'e') {
                try {
                    PrintWriter out = new PrintWriter(new FileOutputStream(db));
                    for (int i = 0; i <= counterStudent; i++) {
                        for (int j = 0; j < listaStudenti[i].id.length; j++) {
                            if (j == listaStudenti[i].id.length - 1) {
                                out.print(listaStudenti[i].id[j] + ", ");
                                break;
                            }
                            out.print(listaStudenti[i].id[j]);
                        }
                        out.print(listaStudenti[i].nume + ", ");
                        out.print(listaStudenti[i].prenume + ", ");
                        out.print(listaStudenti[i].email + ", ");
                        out.println(listaStudenti[i].grupa);
                    }
                    out.close();
                    //powerOn = false;
                } catch (IOException e) {
                    System.out.println(e);
                }
                
                try {
                    PrintWriter out = new PrintWriter(new FileOutputStream(dbGrade));
                    for (int i = 0; i <= indGrades; i++) {
                        for (int j = 0; j < listaNote[i].id.length; j++) {
                            if (j == listaNote[i].id.length - 1) {
                                out.print(listaNote[i].id[j] + ", ");
                                break;
                            }
                            out.print(listaNote[i].id[j]);
                        }
                        for (int j = 0; j < listaNote[i].student_id.length; j++) {
                            if (j == listaNote[i].student_id.length - 1) {
                                out.print(listaNote[i].student_id[j] + ", ");
                                break;
                            }
                            out.print(listaNote[i].student_id[j]);
                        }
                        for (int j = 0; j < listaNote[i].disciplina_id.length; j++) {
                            if (j == listaNote[i].disciplina_id.length - 1) {
                                out.print(listaNote[i].disciplina_id[j] + ", ");
                                break;
                            }
                            out.print(listaNote[i].disciplina_id[j]);
                        }
                        out.print(listaNote[i].nota + ", ");
                        out.println(listaNote[i].data_notarii);
                    }
                    
                    System.out.println("Shutting down...");
                    out.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
                powerOn = false;
            }
            
            
        } while (powerOn == true);
        usrInput.close();
    }
}