package pl.air.hospital.init;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.air.hospital.model.Department;
import pl.air.hospital.model.Doctor;
import pl.air.hospital.model.Nurse;
import pl.air.hospital.model.Patient;
import pl.air.hospital.model.Room;
import pl.air.hospital.repo.DepartmentRepository;
import pl.air.hospital.repo.DoctorRepository;
import pl.air.hospital.repo.NurseRepository;
import pl.air.hospital.repo.PatientRepository;
import pl.air.hospital.repo.RoomRepository;

@Repository
public class DataServiceOne implements DataService {
   
	@Autowired private DepartmentRepository depRepo;
	@Autowired private DoctorRepository docRepo;
	@Autowired private NurseRepository nurRepo;
	@Autowired private PatientRepository patRepo;
	@Autowired private RoomRepository roomRepo;
	
	@Override @Transactional
	public void insertData() {
		
		// department
		
		Department sor          = createDepartment("SOR",                         "Piętro I", "150");
		Department chirurgia    = createDepartment("Chirurgia ogólna",            "Piętro II", "200");
		Department onkologia    = createDepartment("Onkologia",                   "Piętro III", "200");
		Department chorobyWewn  = createDepartment("Oddział Chorób Wewnętrznych", "Piętro IV", "110");
		Department okulistyka   = createDepartment("Okulistyka",                  "Piętro V", "80");
		Department neurologia   = createDepartment("Neurologia",                  "Piętro VI", "95");
		Department laryngologia = createDepartment("Laryngologia",                "Piętro VII", "100");
		
		// doctor
		
		Doctor urbaniak      = createDoctor("Marek",    "Urbaniak",       "st. lek. n. med.",                "2010-05-23", "10500", chirurgia);
		Doctor staszak       = createDoctor("Wojciech",  "Staszak",       "dr n. med.",                      "2015-05-14", "7500", chirurgia);
		Doctor kowalski      = createDoctor("Krzysztof", "Kowalski",      "st. lek. n. med.",                "2009-07-26", "11000", sor);
		Doctor jackowski     = createDoctor("Hubert",    "Jackowski",     "lek. n. med.",                    "2018-07-27", "4500", sor);
		Doctor ratajczak     = createDoctor("Paulina",   "Ratajczak",     "dr n. med.",                      "2017-11-05", "6000", okulistyka);
		Doctor wojciechowska = createDoctor("Magdalena", "Wojciechowska", "mł. asystentka lekarza",          "2020-04-03", "3500", onkologia);
		Doctor polak         = createDoctor("Władysław", "Polak",         "dr n. med.",                      "2011-02-24", "9000", chorobyWewn);
		Doctor wolny         = createDoctor("Mirosław",  "Wolny",         "lek. n. med.",                    "2014-06-01", "5300", neurologia);
		Doctor trafas        = createDoctor("Alex",      "Trafas",        "prof. dr hab. n. med.",           "2009-10-29", "9850", laryngologia);
		Doctor kaczmarek     = createDoctor("Piotr",     "Kaczmarek",     "ordynator prof. dr hab. n. med.", "2007-03-13", "15500", onkologia);
		
		// nurse
		
		Nurse walaszczyk  = createNurse("Agnieszka",  "Walaszczyk",  "2014-06-12", "3500", onkologia);
		Nurse kasperczyk  = createNurse("Weronika",   "Kasperczyk",  "2015-03-15", "5200", sor);
		Nurse stankiewicz = createNurse("Małgorzata", "Stankiewicz", "2019-09-19", "2800", laryngologia);
		Nurse wysocka     = createNurse("Magdalena",  "Wysocka",     "2017-12-01", "3400", neurologia);
		Nurse rybarczyk   = createNurse("Wiesława",   "Rybarczyk",   "2020-11-15", "2950", chorobyWewn);
		Nurse szczepaniak = createNurse("Olga",       "Szczepaniak", "2017-07-10", "4200", okulistyka);
		Nurse koral       = createNurse("Dominika",   "Koral",       "2015-04-23", "3700", chirurgia);
		Nurse pyrka       = createNurse("Emilia",     "Pyrka",       "2011-10-28", "3800", onkologia);
		
		// room
		
	    Room room1   = createRoom("101", sor);
		Room room2   = createRoom("105", sor);
		Room room3   = createRoom("107", sor);
		Room room4   = createRoom("202", chirurgia);
		Room room5   = createRoom("205", chirurgia);
		Room room6   = createRoom("306", onkologia);
		Room room7   = createRoom("405", chorobyWewn);
		Room room8   = createRoom("414", chorobyWewn);
		Room room9   = createRoom("509", okulistyka);
		Room room10  = createRoom("600", neurologia);
		Room room11  = createRoom("702", laryngologia);
		
		// patient
		
		 Patient ml = createPatient("Mariusz",  "Leśniak",     "mężczyzna", "35", "Zerwanie więzadeł krzyżowych w prawym kolanie",      "2020-06-13", urbaniak, chirurgia, koral, room4);
		 Patient mk = createPatient("Michał",   "Kula",        "mężczyzna", "74", "Nowotwór skóry",                                     "2019-10-15", kaczmarek, onkologia, pyrka, room6);
		 Patient kn = createPatient("Kamila",   "Nowak",       "kobieta",   "69", "Operacja zaćmy w oku prawym",                        "2020-06-24", ratajczak, okulistyka, szczepaniak, room9);
		 Patient eb = createPatient("Elżbieta", "Balcerowicz", "kobieta",   "26", "Szumy uszne",                                        "2020-06-21", trafas, laryngologia, stankiewicz, room11);
		 Patient al = createPatient("Alicja",   "Lisiecka",    "kobieta",   "57", "Choroba wrzodowa - żołądek",                         "2019-11-25", polak, chorobyWewn, rybarczyk, room8);
		 Patient wp = createPatient("Wanda",    "Pietrzak",    "kobieta",   "39", "Alzheimer",                                          "2020-06-11", wolny, neurologia, wysocka, room10);
		 Patient tw = createPatient("Tadeusz",  "Wojtkowiak",  "mężczyzna", "68", "Uszkodzone narządy wewnętrzne - stan śpiączki",      "2020-05-24", jackowski, sor, kasperczyk, room3);
		 Patient mg = createPatient("Marcin",   "Góra",        "mężczyzna", "21", "Złamanie kości piszczelowej - prawa noga",           "2020-06-07", staszak, chirurgia, koral, room4);
		 Patient dp = createPatient("Damian",   "Pokrzywka",   "mężczyzna", "52", "Nowotwór trzustki",                                  "2019-11-04", kaczmarek, onkologia, walaszczyk, room6);
		 Patient ag = createPatient("Anna",     "Grzelak",     "kobieta",   "18", "Wielokrotne złamania i urazy narządów wewnętrznych", "2020-06-24", jackowski, sor, kasperczyk, room3);
		 
		 depRepo.saveAll(Arrays.asList(sor,chirurgia, onkologia, chorobyWewn, okulistyka, neurologia, laryngologia));
		 docRepo.saveAll(Arrays.asList(urbaniak, staszak, kowalski, jackowski, ratajczak, wojciechowska, polak, wolny, trafas, kaczmarek));
		 nurRepo.saveAll(Arrays.asList(walaszczyk, kasperczyk, stankiewicz, wysocka, rybarczyk, szczepaniak, koral, pyrka));
		 roomRepo.saveAll(Arrays.asList(room1, room2, room3, room4, room5, room6, room7, room8, room9, room10, room11));
		 patRepo.saveAll(Arrays.asList(ml, mk, kn, eb, al, wp, tw, mg, dp, ag));
		 
	}

	private Department createDepartment(String name, String location, String capacity) {
		return Department.builder()
				         .name(name)
				         .location(location)
				         .capacity(Long.parseLong(capacity))
				         .build();
	}
	
	private Doctor createDoctor(String firstName, String lastName, String position, String hireDate, String salary, Department department) {
		return Doctor.builder()
				     .firstName(firstName)
				     .lastName(lastName)
				     .position(position)
				     .hireDate(LocalDate.parse(hireDate))
				     .salary(new BigDecimal(salary))
				     .department(department)
				     .build();
	}
	
	private Nurse createNurse(String firstName, String lastName, String hireDate, String salary, Department department) {
		return  Nurse.builder()
				     .firstName(firstName)
				     .lastName(lastName)
				     .hireDate(LocalDate.parse(hireDate))
				     .salary(new BigDecimal(salary))
				     .department(department)
				     .build();
	}
	
	private Patient createPatient(String firstName, String lastName, String sex, String age, String sickness, String admissionDate, Doctor doctor, Department department, Nurse nurse, Room room) {
		return Patient.builder()
				      .firstName(firstName)
				      .lastName(lastName)
				      .sex(sex)
				      .age(Integer.parseInt(age))
				      .sickness(sickness)
				      .admissionDate(LocalDate.parse(admissionDate))
				      .doctor(doctor)
				      .department(department)
				      .nurse(nurse)
				      .room(room)
				      .build();
	}
	
	private Room createRoom(String number, Department department) {
		return Room.builder()
				   .number(Long.parseLong(number))
				   .department(department)
				   .build();
	}
	
}
