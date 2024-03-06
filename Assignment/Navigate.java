import java.io.*;

public class Navigate implements Serializable {

    private DSALinkedList locationList;
    private DSALinkedList pathList;
    private DSALinkedList currentPath = new DSALinkedList();
    private DSALinkedList simplePath = new DSALinkedList();

    private float[] dis;
    private String[] barr;
    private String[] sec;

    private Routes[] routeArr = new Routes[2000];

    private String start;
    private String end;
    private String barrier;
    private String securityLvl;

    private String[] splitBarr;
    private String[] splitSec;

    private int locCount = 0;

    public Navigate() {
        locationList = new DSALinkedList();
        pathList = new DSALinkedList();
    }

    // CHECK IS THERE A LOCATION
    public boolean hasLocation(String locLabel) {
        boolean has = false;
        for (Object o : locationList) {
            if (((Locations) o).getLocName().equals(locLabel)) {
                has = true;
            }
        }
        return has;
    }

    // GET THE LOCATION
    public Locations getLocation(String locationName) throws Exception {
        Locations loc = null;
        if (!hasLocation(locationName)) {
            throw new Exception("Location not found! " + locationName);
        }

        for (Object o : locationList) {
            if (((Locations) o).getLocName().equals(locationName)) {
                loc = (Locations) o;
            }
        }

        return loc;
    }

    // GET THE LOCATION ADJACENTS
    public DSALinkedList getAdjacent(String locationName) throws Exception {
        return getLocation(locationName).getAdjacent();
    }

    // CHECK IS THERE A LOCATION ADJACENT
    public boolean isAdjacent(String fromLocation, String toLocation) throws Exception {
        boolean adjacent = false;
        for (Object o : (getLocation(fromLocation).getAdjacent())) {
            if (((Locations) o).equals(getLocation(toLocation))) {
                adjacent = true;
            }
        }
        return adjacent;
    }

    // ADD A LOCATION
    public void addLocation(String locationName) {
        if (hasLocation(locationName) == false) {
            locationList.insertLast(new Locations(locationName));
            locCount++;
        } else
            System.out.println("Location " + locationName + " already there");
    }

    // REMOVE A LOCATION
    public void removeLocation(String locationName) throws Exception {
        if (hasLocation(locationName) == true) {
            locationList.remove(getLocation(locationName));
            locCount--;
        } else
            throw new Exception("No such a location found");

        for (Object o : locationList) {
            Locations loc = ((Locations) o);
            DSALinkedList adjacents = loc.links;
            for (Object n : adjacents) {
                if (n.toString().equals(locationName)) {
                    adjacents.remove(n);
                }
            }
        }

        for (Object y : pathList) {
            Paths p = ((Paths) y);
            if (((p.getFromLocation()).toString().equals(locationName))
                    || ((p.getToLocation()).toString().equals(locationName))) {
                pathList.remove(p);
            }
        }
    }

    // UPDATE AN EXISTING LOCATION
    public void updateLocation(String currentName, String newName) throws Exception {
        if (hasLocation(currentName) == true) {
            getLocation(currentName).locName = newName;
        } else
            throw new Exception("No such a location found");

        for (Object o : locationList) {
            Locations loc = ((Locations) o);
            DSALinkedList adjacents = loc.links;
            for (Object n : adjacents) {
                if (n.toString().equals(currentName)) {
                    n = newName;
                }
            }
        }

        for (Object y : pathList) {
            Paths p = ((Paths) y);
            if ((p.getFromLocation()).toString().equals(currentName)) {
                p.fromLocation = newName;
            } else if ((p.getToLocation()).toString().equals(currentName)) {
                p.toLocation = newName;
            }
        }
    }

    // ADD A PATH
    public void addPath(String fromLocation, String toLocation, float distance, String security, String barrier)
            throws Exception {
        if (!hasLocation(fromLocation)) {
            addLocation(fromLocation);
        }

        if (!hasLocation(toLocation)) {
            addLocation(toLocation);
        }

        for (Object o : locationList) {
            if (((Locations) o).equals(getLocation(fromLocation))) {
                getLocation(fromLocation).addPath(getLocation(toLocation));
                pathList.insertLast(new Paths(fromLocation, toLocation, distance, security, barrier));
            }
        }
    }

    // REMOVE A PATH
    public void removePath(String from, String to) throws Exception {

        if (isAdjacent(from, to)) {
            for (Object y : pathList) {
                Paths p = ((Paths) y);
                if (((p.getFromLocation()).toString().equals(from))
                        && ((p.getToLocation()).toString().equals(to))) {
                    pathList.remove(p);
                }
            }

            for (Object o : locationList) {
                Locations loc = ((Locations) o);
                DSALinkedList adjacents = loc.links;
                for (Object n : adjacents) {
                    if ((loc.getLocName().equals(from)) || (n.toString().equals(to))) {
                        adjacents.remove(n);
                    }
                }
            }
        } else
            throw new Exception("No such a path found");
    }

    // UPDATE AN EXISTING PATH
    public void updatePath(String from, String to, float preDistance, float inDistance, String inSecurity,
            String inBarrier)
            throws Exception {
        if (isAdjacent(from, to)) {
            for (Object y : pathList) {
                Paths p = ((Paths) y);
                if (((p.getFromLocation()).toString().equals(from)) && ((p.getToLocation()).toString().equals(to))
                        && ((p.getDistance()) == preDistance)) {
                    p.distance = inDistance;
                    p.security = inSecurity;
                    p.barrier = inBarrier;
                }
            }
        } else
            throw new Exception("No such a path found");
    }

    // CLEARING THE VISITED LOCATIONS
    public void clearVisited() {
        for (Object o : locationList) {
            ((Locations) o).clearVisited();
        }
    }

    // GET THE DISTANCE BETWEEN TWO LOCATIONS
    public void distance(String s, String e) {
        dis = new float[2000];
        int i = 0;
        for (Object object : pathList) {
            if (((Paths) object).fromLocation.equals(s) && ((Paths) object).toLocation.equals(e)) {

                dis[i] = ((Paths) object).getDistance();
                i++;
            }
        }
        i = 0;
        for (int j = 0; j < dis.length; j++) {
            if (dis[j] == 0) {
                for (int jj = j; jj < dis.length; jj++) {
                    dis[jj] = dis[i];
                    i++;
                }
            }
        }
    }

    // GET THE SECURITY INFO BETWEEN TWO LOCATIONS
    public void security(String s, String e) {
        sec = new String[2000];
        int i = 0;
        for (Object object : pathList) {
            if (((Paths) object).fromLocation.equals(s) && ((Paths) object).toLocation.equals(e)) {
                sec[i] = ((Paths) object).getSecurity().toString();
                i++;
            }
        }
        i = 0;
        for (int j = 0; j < sec.length; j++) {
            if (sec[j] == null) {
                for (int jj = j; jj < sec.length; jj++) {
                    sec[jj] = sec[i];
                    i++;
                }
            }
        }
    }

    // GET THE BARRIER INFO BETWEEN TWO LOCATIONS
    public void barrier(String s, String e) {
        barr = new String[2000];
        int i = 0;
        for (Object object : pathList) {
            if (((Paths) object).fromLocation.equals(s) && ((Paths) object).toLocation.equals(e)) {
                barr[i] = ((Paths) object).getBarrier().toString();
                i++;
            }
        }
        i = 0;
        for (int j = 0; j < barr.length; j++) {
            if (barr[j] == null) {
                for (int jj = j; jj < barr.length; jj++) {
                    barr[jj] = barr[i];
                    i++;
                }
            }
        }
    }

    // GET ALL POSSIBLE ROUTES BETWEEN START TO END
    /*
     * Reference: www.baeldung.com (by Said Sryheni)
     * Date: 10/19/2020
     * Link: https://www.baeldung.com/cs/simple-paths-between-two-vertices
     */
    public void go(String from, String to) throws Exception {
        try {
            Locations start = getLocation(from);
            Locations end = getLocation(to);

            if (start.getVisited()) {
                return;
            }
            start.setVisited();
            currentPath.insertLast(start);
            if (start == end) {
                DSALinkedList copy = new DSALinkedList();
                Object c;
                for (Object o : currentPath) {
                    c = (Object) o;
                    copy.insertLast(c);

                }
                simplePath.insertLast(copy);
                start.clearVisited();
                currentPath.removeLast();
                return;
            }
            for (Object o : start.getAdjacent()) {
                Locations currentVertex = (Locations) o;
                go(currentVertex.getLocName(), end.getLocName());
            }

            currentPath.removeLast();
            start.clearVisited();
        } catch (Exception e) {
            System.out.println("Routes not found!");
        }

    }

    // FILTERING THE ALL POSIBLE ROUTES
    public void filterPaths() {
        String out = "";
        String secOut = "";
        String barrOut = "";
        Locations temp = null;
        int count = 0;
        int tracker = 0;
        boolean pass = false;
        int ii = 0;
        int idx = 0;
        int routes = 0;
        float totalDistance;
        float[] usedDis = new float[2000];
        String[] usedSec = new String[2000];
        String[] usedBarr = new String[2000];

        DSALinkedList c;
        for (Object o : simplePath) {
            totalDistance = 0;
            c = (DSALinkedList) o;

            Object cc;
            for (Object oo : c) {
                cc = (Object) oo;
                if (count > 0) {
                    distance(temp.getLocName(), ((Locations) cc).getLocName());
                    security(temp.getLocName(), ((Locations) cc).getLocName());
                    barrier(temp.getLocName(), ((Locations) cc).getLocName());

                    if (pass) {
                        if (usedDis[idx - tracker] == dis[ii]) {
                            ii++;
                        }
                    }
                    float t = dis[ii];
                    String s = sec[ii];
                    String b = barr[ii];

                    totalDistance += t;
                    secOut += s + " ";
                    barrOut += b + " ";

                    usedDis[idx] = t;
                    usedSec[idx] = s;
                    usedBarr[idx] = b;
                    idx++;
                }
                temp = (Locations) cc;
                out += cc.toString() + " -> ";
                count++;
            }
            pass = true;

            boolean checker = true;

            // CHECK IF THERE ANY BARRIERS TO AVOID
            for (int i = 0; i < splitBarr.length; i++) {
                if ((!splitBarr[i].equals("no")) && (barrOut.matches("(.*)" + splitBarr[i].toString() + "(.*)"))) {
                    checker = false;
                    break;
                }
            }

            // CHECK IS THIS ROUTE INCLUDED RIGHT SECURITY LEVELS FOR TRAVELLER
            if (checker) {
                for (int i = 0; i < splitSec.length; i++) {
                    String[] secondSplit = secOut.toString().split(" ");
                    for (int j = 0; j < secondSplit.length; j++) {
                        if ((secondSplit[j].toString().matches("(.*)" + splitSec[i].toString() + "(.*)"))
                                || (secondSplit[j].toString().matches("(.*)" + "no" + "(.*)"))) {
                            checker = true;
                        } else {
                            checker = false;
                            break;
                        }
                    }
                }
            }

            if (checker) {
                routeArr[routes] = new Routes(out, totalDistance, secOut, barrOut);
                routes++;
            }
            out = "";
            secOut = "";
            barrOut = "";
            tracker = count - 1;
            count = 0;
            temp = null;
        }
        rankRoutes(routeArr);
    }

    // GET JOURNEY DETAILS FROM USER
    public void getJourney(String from, String to, String barr, String security) {
        start = from;
        end = to;
        barrier = barr;
        securityLvl = security;
    }

    // GENARATE ROUTES
    public void genRoutes() throws Exception {
        try {
            splitBarr = barrier.split(",");
            splitSec = securityLvl.split(",");
            go(start, end);
            filterPaths();
            System.out.println("Routes generated successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // RANK THE FILTERED ROUTES
    public void rankRoutes(Routes[] isArr) {
        for (int nn = 1; nn <= isArr.length - 1; nn++) {
            int jj = nn;
            if (isArr[jj] != null) {
                while ((jj > 0) && (isArr[jj - 1].getTotalDistance()) > (isArr[jj].getTotalDistance())) {
                    Routes tem = isArr[jj];
                    isArr[jj] = isArr[jj - 1];
                    isArr[jj - 1] = tem;
                    jj = jj - 1;
                }
            }
        }
    }

    // PRINT THE ROUTES
    public void printRoutes() {
        boolean found = false;
        String PURPLE = "\u001B[35m";
        String DEFAULT = "\033[0m";

        System.out.println("~ Filtered Routes ~\n");
        for (int i = 0; i < routeArr.length; i++) {
            if (routeArr[i] != null) {
                System.out.println(routeArr[i].toString());
                System.out.println("Total distance = " + routeArr[i].getTotalDistance());
                System.out.println("Security levels = " + routeArr[i].getSecurity());
                System.out.println("Barriers = " + routeArr[i].getBarrier());
                System.out.println(
                        PURPLE + "\n--------------------------------------------------------------------------------------------\n"
                                + DEFAULT);
                found = true;
            }
        }
        System.out.println(DEFAULT);
        if (!found) {
            System.out.println("Routes not found!");
        }
    }

    // CLEAR ROUTE ARRAY
    public void clearPaths() {
        for (int i = 0; i < routeArr.length; i++) {
            if (routeArr[i] != null) {
                routeArr[i] = null;
            }
        }
    }

    // CLEAR LINKED LISTS
    public void clearLists() throws Exception {
        while (!simplePath.isEmpty()) {
            simplePath.removeLast();
        }

        while (!currentPath.isEmpty()) {
            currentPath.removeLast();
        }
    }

    // PRINT MAP DETAILS
    public void printWorld() {
        String GREEN = "\u001B[32m";
        String CYAN = "\u001B[36m";
        String DEFAULT = "\033[0m";

        System.out
                .println(GREEN + "================================== WORLD ==================================" + DEFAULT);
        System.out.printf("%-16s%-16s%-15s%-15s%-15s\n", "FROM", "TO", "DISTANCE", "SECURITY LVL", "BARRIERS");
        System.out.println(CYAN);
        for (Object o : pathList) {
            System.out.printf("%-16s%-16s%-15f%-15s%-15s\n", (((Paths) o).getFromLocation()),
                    (((Paths) o).getToLocation()),
                    (((Paths) o).getDistance()), (((Paths) o).getSecurity()), (((Paths) o).getBarrier()));
            System.out.println(
                    DEFAULT + "---------------------------------------------------------------------------" + CYAN);
        }
        System.out.println(DEFAULT);
    }

    // SAVE THE WORLD
    public void saveWorld(String pFile) {
        FileOutputStream outputStream = null;
        PrintWriter pw;

        try {
            outputStream = new FileOutputStream(pFile);
            pw = new PrintWriter(outputStream);
            pw.println("================================== WORLD ==================================");
            pw.printf("%-16s%-16s%-15s%-15s%-15s\n", "FROM", "TO", "DISTANCE", "SECURITY LVL", "BARRIERS");
            for (Object o : pathList) {
                pw.printf("%-16s%-16s%-15f%-15s%-15s\n", (((Paths) o).getFromLocation()),
                        (((Paths) o).getToLocation()),
                        (((Paths) o).getDistance()), (((Paths) o).getSecurity()), (((Paths) o).getBarrier()));
                pw.println("---------------------------------------------------------------------------");
            }
            pw.close();
            System.out.println("File (" + pFile + ") saved successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // DISPLAY THE GRAPH AS A MATRIX
    public void displayAsMatrix() throws Exception {
        Locations[] tempArr = new Locations[locCount];
        int[][] matrix = new int[locCount][locCount];
        int[] lableArr = new int[locCount];
        int k = 0;
        String YELLOW = "\u001B[33m";
        String DEFAULT = "\033[0m";

        for (Object o : locationList) {
            tempArr[k] = (Locations) o;
            lableArr[k] = k + 1;
            k++;
        }

        for (int i = 0; i < locCount; i++) {
            for (int j = 0; j < locCount; j++) {
                if (isAdjacent(tempArr[i].getLocName(), tempArr[j].getLocName())) {
                    distance(tempArr[i].getLocName(), tempArr[j].getLocName());
                    matrix[i][j] = (int) dis[1];
                } else {
                    matrix[i][j] = 0;
                }
            }
        }

        System.out.println("  ~ weighted adjacency matrix ~  \n");

        System.out.print(YELLOW + "   ");
        for (int i = 0; i < matrix.length; i++) {
            System.out.printf("%3d", lableArr[i]);
        }
        System.out.print(DEFAULT);
        System.out.println();
        for (int i = 0; i < locCount; i++) {
            System.out.printf(YELLOW + "%3d" + DEFAULT, lableArr[i]);
            for (int j = 0; j < locCount; j++) {
                System.out.printf("%3d", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Legend: ");
        for (int i = 0; i < lableArr.length; i++) {
            System.out.printf(YELLOW + "%4d" + DEFAULT + "%4s%-15s\n", lableArr[i], " - ", tempArr[i]);
        }

        System.out.println();
    }

    // SAVE THE GRAPH AS A MATRIX
    public void saveMatrix(String pFile) throws Exception {
        Locations[] tempArr = new Locations[locCount];
        int[][] matrix = new int[locCount][locCount];
        int[] lableArr = new int[locCount];
        int k = 0;

        FileOutputStream outputStream = null;
        PrintWriter pw;

        try {
            outputStream = new FileOutputStream(pFile);
            pw = new PrintWriter(outputStream);

            pw.println("~ Graph as a matrix ~\n");

            for (Object o : locationList) {
                tempArr[k] = (Locations) o;
                lableArr[k] = k + 1;
                k++;
            }

            for (int i = 0; i < locCount; i++) {
                for (int j = 0; j < locCount; j++) {
                    if (isAdjacent(tempArr[i].getLocName(), tempArr[j].getLocName())) {
                        distance(tempArr[i].getLocName(), tempArr[j].getLocName());
                        matrix[i][j] = (int) dis[1];
                    } else {
                        matrix[i][j] = 0;
                    }
                }
            }

            pw.println("  ~ weighted adjacency matrix ~  \n");

            pw.print("   ");
            for (int i = 0; i < matrix.length; i++) {
                pw.printf("%3d", lableArr[i]);
            }

            pw.println();
            for (int i = 0; i < locCount; i++) {
                pw.printf("%3d", lableArr[i]);
                for (int j = 0; j < locCount; j++) {
                    pw.printf("%3d", matrix[i][j]);
                }
                pw.println();
            }

            pw.println();
            pw.println("Legend: ");
            for (int i = 0; i < lableArr.length; i++) {
                pw.printf("%4d%4s%-15s\n", lableArr[i], " - ", tempArr[i]);
            }

            pw.close();
            System.out.println("File (" + pFile + ") saved successfully.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // LOAD THE MAP FILE
    public void loadFile(String pFile) throws Exception {
        String line;
        FileInputStream stream = null;
        InputStreamReader reader;
        BufferedReader bfrReader;
        int direction = -1;

        try {
            stream = new FileInputStream(pFile);
            reader = new InputStreamReader(stream);
            bfrReader = new BufferedReader(reader);

            while ((line = bfrReader.readLine()) != null) {
                if (!(line.charAt(0) == '#')) {

                    if (line.matches("(.*)" + "<>" + "(.*)")) {
                        direction = 0;
                    } else if (line.matches("(.*)" + ">" + "(.*)")) {
                        direction = 1;
                    } else if (line.matches("(.*)" + "<" + "(.*)")) {
                        direction = 2;
                    }

                    String[] splitArray = line.split("(\\|)|(<>)|(>)|(<)");
                    String[] splitDistance = splitArray[2].split(":");
                    String[] splitSecurity = splitArray[3].split(":");
                    String[] splitBarrier = splitArray[4].split(":");

                    if (splitSecurity.length > 1)
                        splitSecurity[0] = splitSecurity[1];
                    else
                        splitSecurity[0] = "no";

                    if (splitBarrier.length > 1)
                        splitBarrier[0] = splitBarrier[1];
                    else
                        splitBarrier[0] = "no";

                    if (direction == 0) {
                        addPath(splitArray[0], splitArray[1], Float.parseFloat(splitDistance[1]), splitSecurity[0],
                                splitBarrier[0]);
                        addPath(splitArray[1], splitArray[0], Float.parseFloat(splitDistance[1]), splitSecurity[0],
                                splitBarrier[0]);
                    } else if (direction == 1) {
                        addPath(splitArray[0], splitArray[1], Float.parseFloat(splitDistance[1]), splitSecurity[0],
                                splitBarrier[0]);
                    } else if (direction == 2) {
                        addPath(splitArray[1], splitArray[0], Float.parseFloat(splitDistance[1]), splitSecurity[0],
                                splitBarrier[0]);
                    }
                }
            }
            stream.close();
            System.out.println(pFile + " File Loaded.");
        } catch (IOException e) {
            System.out.println("ERROR While File loading: " + e.getMessage());
        }
    }

    // READ THE JOURNEY FILE
    public void readJourneyFile(String pFile) throws Exception {
        String line;
        FileInputStream stream = null;
        InputStreamReader reader;
        BufferedReader bfrReader;
        int l = 0;
        String from = null;
        String to = null;
        String time = null;
        String b = null;
        String s = null;

        try {
            stream = new FileInputStream(pFile);
            reader = new InputStreamReader(stream);
            bfrReader = new BufferedReader(reader);

            while ((line = bfrReader.readLine()) != null) {
                if (!(line.charAt(0) == '#')) {

                    String[] splitArray = line.split(" ");
                    if (l == 0)
                        from = splitArray[1];
                    else if (l == 1)
                        to = splitArray[1];
                    else if (l == 2)
                        time = splitArray[1];
                    else if (l == 3)
                        b = splitArray[1];
                    else if (l == 4)
                        s = splitArray[1];
                    l++;
                }
            }
            stream.close();
            splitBarr = b.split(",");
            splitSec = s.split(",");

            go(from, to);
            filterPaths();
            System.out.println(pFile + " File Loaded.");

        } catch (IOException e) {
            System.out.println("ERROR While File loading: " + e.getMessage());
        }
    }

    // SAVE FILTERED ROUTES TO AN ARRAY
    public void saveFile(String pFile) {
        FileOutputStream outputStream = null;
        PrintWriter pw;
        int ct = 0;

        try {
            outputStream = new FileOutputStream(pFile);
            pw = new PrintWriter(outputStream);

            boolean found = false;
            pw.println("~ Filtered Routes ~\n");
            for (int i = 0; i < routeArr.length; i++) {
                if (routeArr[i] != null) {
                    pw.println(routeArr[i].toString());
                    pw.println("Total distance = " + routeArr[i].getTotalDistance());
                    pw.println("Security levels = " + routeArr[i].getSecurity());
                    pw.println("Barriers = " + routeArr[i].getBarrier());
                    pw.println(
                            "\n--------------------------------------------------------------------------------------------\n");
                    found = true;
                    ct++;
                }
            }
            if (!found) {
                pw.println("Routes not found!");
            }
            pw.close();
            System.out.println("File (" + pFile + ") saved successfully.");
            System.out.println("Route count: " + ct);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // CHANGE DISTANCE UNIT
    public void changeUnit(String fromUnit, String toUnit) {
        float temp = 0;
        for (Object o : pathList) {
            // M TO KM
            if ((fromUnit.equals("m")) && (toUnit.equals("km"))) {
                temp = (((Paths) o).getDistance()) / 1000;
                // KM TO M
            } else if ((fromUnit.equals("km")) && (toUnit.equals("m"))) {
                temp = (((Paths) o).getDistance()) * 1000;
            } else {
                temp = (((Paths) o).getDistance());
            }
            ((Paths) o).distance = temp;
        }

        System.out.println("Distance unit converted successfully.");
    }

    // SAVE NETWORK TO A SERIALIZED FILE
    public void saveNetwork(Navigate pObject, String pFile) {
        FileOutputStream fileStrm;
        ObjectOutputStream objStrm;
        try {
            fileStrm = new FileOutputStream(pFile);
            objStrm = new ObjectOutputStream(fileStrm);
            objStrm.writeObject(pObject);
            objStrm.close();
            System.out.println("Network saved.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Unable to save network");
        }
    }

    // LOAD NETWORK FROM A SERIALIZED FILE
    public Navigate loadNetwork(String pFile) throws IllegalArgumentException {
        FileInputStream fileStrm;
        ObjectInputStream objStrm;
        Navigate inObj;
        try {
            fileStrm = new FileInputStream(pFile);
            objStrm = new ObjectInputStream(fileStrm);
            inObj = (Navigate) objStrm.readObject();
            objStrm.close();
            System.out.println("Network Loaded.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Unable to load network");
        }
        return inObj;
    }

    // LOCATION CLASS (PRIVATE INNER CLASS): TO STORE LOCATION (VERTEX) DETAILS
    private class Locations implements Serializable {
        private String locName;
        private DSALinkedList links;
        private boolean visited;

        public Locations(String inName) {
            locName = inName;
            links = new DSALinkedList();
        }

        public String getLocName() {
            return locName;
        }

        public DSALinkedList getAdjacent() {
            DSALinkedList newLink = new DSALinkedList();
            for (Object o : links) {
                newLink.insertLast(o);
            }
            return newLink;
        }

        public void addPath(Locations location) {
            links.insertLast(location);
        }

        public boolean getVisited() {
            return visited;
        }

        public void setVisited() {
            visited = true;
        }

        public void clearVisited() {
            visited = false;
        }

        public String toString() {
            return locName;
        }
    }

    // PATH CLASS (PRIVATE INNER CLASS): TO STORE PATH (EDGE) DETAILS
    private class Paths implements Serializable {
        private String fromLocation;
        private String toLocation;
        private float distance;
        private Object security;
        private Object barrier;

        public Paths(String fromVertex, String toVertex, float inDistance, String inSecurity, String inBarrier) {
            fromLocation = fromVertex;
            toLocation = toVertex;
            distance = inDistance;
            security = inSecurity;
            barrier = inBarrier;
        }

        public float getDistance() {
            return distance;
        }

        public String getFromLocation() {
            return fromLocation;
        }

        public String getToLocation() {
            return toLocation;
        }

        public Object getSecurity() {
            return security;
        }

        public Object getBarrier() {
            return barrier;
        }
    }

    // ROUTES CLASS (PRIVATE INNER CLASS): TO STORE FILTERED ROUTES
    private class Routes implements Serializable {
        private String route;
        private float totalDistance;
        private String security;
        private String barrier;

        public Routes(String r, float d, String s, String b) {
            route = r;
            totalDistance = d;
            security = s;
            barrier = b;
        }

        public String toString() {
            return route;
        }

        public float getTotalDistance() {
            return totalDistance;
        }

        public String getSecurity() {
            return security;
        }

        public String getBarrier() {
            return barrier;
        }
    }

}
