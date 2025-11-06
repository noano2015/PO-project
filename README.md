# Veterinary Hotel Management System

A comprehensive Java-based hotel management system for a veterinary facility featuring keepers, veterinarians, habitats with trees, and advanced satisfaction algorithms. Built with object-oriented design patterns and extensible architecture.

## 📋 Project Overview

This system manages a veterinary hotel where animals of various species are cared for by keepers (responsible for feeding and habitat cleaning) and veterinarians (responsible for animal health through vaccinations). The application calculates satisfaction levels for both animals and staff based on complex formulas considering multiple environmental and social factors.

## 🐾 Core Entities

### Animals & Species

**Species**
- Unique identifier (case-insensitive string)
- Unique name
- Registry of all animals belonging to the species

**Animals**
- Unique identifier (case-insensitive string)
- Name (non-unique)
- Species association
- Health status history (vaccination events)
- Habitat assignment

**Animal Satisfaction Formula:**
```
satisfaction(a) = 20 + 3×same_species(a,h) - 2×different_species(a,h) 
                  + area(h)/population(h) + suitability(a,h)
```
Where suitability is: +20 (positive), -20 (negative), or 0 (neutral - default)

### Habitats

**Properties:**
- Unique identifier (case-insensitive string)
- Name (non-unique)
- Area (integer)
- Tree associations
- Animal population
- Species suitability influences

Habitats can be more or less suitable for specific species, directly impacting animal satisfaction.

### Trees

**Tree Types:**
- 🍂 **Deciduous (CADUCA)**: Lose leaves mainly in autumn, bare in winter
- 🌲 **Evergreen (PERENE)**: Lose some leaves year-round, more in winter

**Properties:**
- Unique identifier (case-insensitive string)
- Name (non-unique)
- Age in years (integer)
- Base cleaning difficulty (integer)
- Biological cycle based on current season

**Cleaning Effort Formula:**
```
cleaning_effort(t) = base_difficulty(t) × seasonal_effort(t) × log(age(t) + 1)
```

**Seasonal Effort Table:**
| Season | Winter | Spring | Summer | Autumn |
|--------|--------|--------|--------|--------|
| Deciduous | 0 | 1 | 2 | 5 |
| Evergreen | 2 | 1 | 1 | 1 |

**Biological Cycle States:**
| Type | Winter | Spring | Summer | Autumn |
|------|--------|--------|--------|--------|
| CADUCA | SEMFOLHAS | GERARFOLHAS | COMFOLHAS | LARGARFOLHAS |
| PERENE | LARGARFOLHAS | GERARFOLHAS | COMFOLHAS | COMFOLHAS |

Trees age by 1 year every 4 seasons and follow the application's current season.

### Employees

**Keepers (TRT)**
- Responsible for habitat maintenance
- Assigned to zero or more habitats
- Affected by cleaning workload

**Keeper Satisfaction Formula:**
```
satisfaction(k) = 300 - work(k)
work(k) = Σ (habitat_work(h) / keepers_for_habitat(h)), h ∈ habitats(k)
habitat_work(h) = area(h) + 3×population(h) + Σ cleaning_effort(t), t ∈ trees(h)
```

**Veterinarians (VET)**
- Authorized to vaccinate specific species
- Assigned to zero or more species
- Affected by animal population workload

**Veterinarian Satisfaction Formula:**
```
satisfaction(v) = 20 - work(v)
work(v) = Σ (population(s) / vets_for_species(s)), s ∈ species(v)
```

### Vaccines

**Properties:**
- Unique identifier (case-insensitive string)
- Name (non-unique)
- List of applicable species
- Complete vaccination history (vaccine, vet, animal, order)

**Vaccination Damage System:**

When a vaccine is administered to an animal:

1. **Correct species**: damage = 0 → **NORMAL**
2. **Wrong species, damage = 0**: → **CONFUSÃO**
3. **Damage 1-4**: → **ACIDENTE**
4. **Damage ≥ 5**: → **ERRO**

**Damage Calculation:**
```
damage(v, a) = MAX(name_size(species(a), s) - common_chars(species(a), s)), 
               s ∈ applicable_species(v)
name_size(s1, s2) = max(length(s1), length(s2))
```

Example: Vaccinating a bird (AVE) with a mammal (MAMÍFERO) vaccine = 6 damage

Health history example: `ACIDENTE,ACIDENTE,ERRO,ERRO,ERRO,NORMAL`

## ✨ Key Features

### Season Management
- Four seasons: Spring (0), Summer (1), Autumn (2), Winter (3)
- Application starts in Spring
- Advance season affects all trees
- Trees age every 4 seasons

### Satisfaction System
- Real-time calculation for animals and employees
- Complex formulas considering multiple factors
- Global satisfaction tracking
- Rounded to nearest integer for display

### Vaccination System
- Authorization checks (vet must be qualified for species)
- Wrong vaccine warnings
- Automatic damage calculation and health history tracking
- Complete vaccination audit trail

### Persistent Storage
- Java serialization for state preservation
- Text file import for initial data loading
- Single active file per session
- Unsaved changes warnings

## 🛠️ Technical Implementation

### Architecture

```
hva/
├── core/                  # Domain logic (hva-core)
│   ├── HotelManager      # Main manager class
│   ├── Animal
│   ├── Species
│   ├── Habitat
│   ├── Tree
│   ├── Employee
│   │   ├── Keeper
│   │   └── Veterinarian
│   └── Vaccine
├── app/                   # User interface (hva-app)
│   ├── main/             # Main menu
│   ├── animal/           # Animal management
│   ├── employee/         # Employee management
│   ├── habitat/          # Habitat management
│   ├── vaccine/          # Vaccine management
│   ├── search/           # Query operations
│   └── exceptions/       # UI exceptions
└── app-support/          # Support libraries
```

### Design Patterns

**1. Strategy Pattern** - Employee Satisfaction Calculation
- Runtime satisfaction policy changes
- New calculation strategies without code modification
- Default formulas as described in specification

**2. State Pattern** - Tree Seasonal Behavior
- Seasonal state transitions
- Extensible for new seasonal behaviors (e.g., leaf color)
- Clean separation of tree type and seasonal logic
- No conditionals in tree code

**3. Open-Closed Principle**
- New employee types with minimal impact
- New publication types
- New search methods
- Multiple hotel instances support

### Exception Handling

| Entity | Exception Class | Thrown When |
|--------|----------------|-------------|
| Animal | `UnknownAnimalKeyException` | Unknown animal ID |
| Animal | `DuplicateAnimalKeyException` | Duplicate animal ID |
| Species | `UnknownSpeciesKeyException` | Unknown species ID |
| Employee | `UnknownEmployeeKeyException` | Unknown employee ID |
| Employee | `DuplicateEmployeeKeyException` | Duplicate employee ID |
| Veterinarian | `UnknownVeterinarianKeyException` | ID not a vet |
| Veterinarian | `VeterinarianNotAuthorizedException` | Vet not qualified |
| Habitat | `UnknownHabitatKeyException` | Unknown habitat ID |
| Habitat | `DuplicateHabitatKeyException` | Duplicate habitat ID |
| Tree | `UnknownTreeKeyException` | Unknown tree ID |
| Tree | `DuplicateTreeKeyException` | Duplicate tree ID |
| Vaccine | `UnknownVaccineKeyException` | Unknown vaccine ID |
| Vaccine | `DuplicateVaccineKeyException` | Duplicate vaccine ID |
| Responsibility | `NoResponsibilityException` | Invalid responsibility |

## 🚀 Usage

### Running the Application

```bash
# Basic execution
java -cp <classpath> hva.app.App

# With initial data import
java -Dimport=test.import -cp <classpath> hva.app.App

# Automated testing
java -Dimport=test.import -Din=test.in -Dout=test.outhyp hva.app.App
diff -b test.out test.outhyp
```

### Menu Structure

**Main Menu:**
- 📂 Create, Open, Save
- 🌱 Advance Season
- 📊 View Global Satisfaction
- 🐾 Animal Management
- 👥 Employee Management
- 🏡 Habitat Management
- 💉 Vaccine Management
- 🔍 Queries

**Animal Management:**
- View all animals
- Register new animal
- Transfer animal to habitat
- Calculate animal satisfaction

**Employee Management:**
- View all employees
- Register new employee
- Add/remove responsibilities
- Calculate employee satisfaction

**Habitat Management:**
- View all habitats
- Register new habitat
- Change habitat area
- Change species influence
- Plant tree in habitat
- View habitat trees

**Vaccine Management:**
- View all vaccines
- Register new vaccine
- Vaccinate animal
- View vaccination history

**Queries:**
- Animals in habitat
- Vaccinations for animal
- Veterinarian's medical acts
- Wrong vaccinations (errors)

## 📥 Input Format

### Text File Import Example

```
ANIMAL|idAnimal|nomeAnimal|idEspécie|idHabitat
ESPÉCIE|idEspécie|nomeEspécie
HABITAT|idHabitat|nomeHabitat|área[|idÁrvore1|...]
ÁRVORE|idÁrvore|nomeÁrvore|idade|dificuldadeBase|tipoÁrvore
TRATADOR|idTratador|nomeTratador[|idHabitat1,...]
VETERINÁRIO|idVet|nomeVet[|idEspécie1,...]
VACINA|idVacina|nomeVacina[|idEspécie1,...]
```

Files are UTF-8 encoded. The application never produces this format (only reads it).

## 📤 Output Formats

### Animal Display
```
ANIMAL|idAnimal|nomeAnimal|idEspécie|healthHistory|idHabitat
ANIMAL|id123|Simba|LEÃO|NORMAL,ACIDENTE|HAB01
ANIMAL|id456|Rex|CÃO|VOID|HAB02
```

### Employee Display
```
VET|idVet|nome|species1,species2
TRT|idKeeper|nome|habitat1,habitat2
VET|v01|Dr. Silva|LEÃO,TIGRE
TRT|k01|João|HAB01,HAB02
```

### Habitat Display
```
HABITAT|idHabitat|nome|área|numTrees
ÁRVORE|idTree|nome|age|baseDifficulty|type|biologicalCycle
```

### Vaccination Record
```
REGISTO-VACINA|idVacina|idVeterinário|idEspécie
```

## 🧪 Testing

The system includes automated testing infrastructure:
- Import initial state from text file
- Execute commands from input file
- Compare output with expected results
- Supports UTF-8 encoded files

## 📚 Technologies Used

- **Language**: Java
- **Design**: Object-Oriented Programming, Design Patterns
- **Persistence**: Java Serialization
- **UI Library**: po-uilib (provided)
- **Support Libraries**: hva-app-support (provided)

## 🎓 Academic Context

This project was developed as part of the Object-Oriented Programming course at Instituto Superior Técnico, Universidade de Lisboa (2011).

**Key Learning Objectives:**
- Advanced OOP concepts
- Design pattern application
- Extensible architecture design
- Complex domain modeling
- Formula-based calculation systems

## ⚙️ Design Requirements

- ✅ Open-Closed Principle compliance
- ✅ Strategy Pattern for satisfaction calculations
- ✅ State Pattern for seasonal tree behavior
- ✅ Support for multiple hotel instances
- ✅ Extensible search system
- ✅ Minimal impact when adding new types

## 📝 License

This is an academic project. Please respect academic integrity policies if you're a student working on a similar assignment.

---

**Note**: This system demonstrates advanced object-oriented design principles including the Strategy and State patterns, complex domain modeling with interdependent entities, and extensible architecture for real-world veterinary facility management.
