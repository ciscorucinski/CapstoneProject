package io.github.ciscorucinski.personal.intro.model;

import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;

public class Resume {

    public static final String DATABASE_NAME = "resume.db";
    public static final int DATABASE_VERSION = 1;

    @AutoValue
    public abstract static class Education implements EducationModel, ModelIndicator {
        public static final Factory<Education> FACTORY = new Factory<>(new Creator<Education>() {

            @Override
            public Education create(long _id, String name, String degree, String location,
                                    String start_date, String end_date, String courses) {

                return new AutoValue_Resume_Education(_id, name, degree, location, start_date, end_date, courses);

            }

        });

        public static final RowMapper<Education> MAPPER = FACTORY.get_all_educationMapper();
    }

    @AutoValue
    public abstract static class Project implements ProjectsModel, ModelIndicator {

        public static final Factory<Project> FACTORY = new Factory<>(new Creator<Project>() {

            @Override
            public Project create(long _id, String name, String link, String location, String year,
                                  String description, String accomplishment1,
                                  String accomplishment2,
                                  String accomplishment3) {

                return new AutoValue_Resume_Project(_id, name, link, location, year, description,
                        accomplishment1, accomplishment2, accomplishment3);
            }
        });

        public static final RowMapper<Project> MAPPER = FACTORY.get_projectsMapper();
    }

    @AutoValue
    public abstract static class Skill implements SkillsModel, ModelIndicator {

        public static final Factory<Skill> FACTORY = new Factory<>(new Creator<Skill>() {

            @Override
            public Skill create(long _id, String type, String level_1_skills, String level_2_skills,
                                String level_3_skills) {

                return new AutoValue_Resume_Skill(_id, type, level_1_skills, level_2_skills, level_3_skills);
            }
        });

        public static final RowMapper<Skill> MAPPER = FACTORY.get_all_skillsMapper();
    }

    @AutoValue
    public abstract static class Work implements WorkModel, ModelIndicator {
        public static final Factory<Work> FACTORY = new Factory<>(new Creator<Work>() {

            @Override
            public Work create(long _id, String name, String location, String position,
                               String start_date, String end_date) {

                return new AutoValue_Resume_Work(_id, name, location, position, start_date, end_date);
            }
        });

        public static final RowMapper<Work> MAPPER = FACTORY.get_all_workMapper();
    }

    @AutoValue
    public abstract static class People implements PeopleModel, ModelIndicator {

        public static final Factory<People> FACTORY = new Factory<>(new Creator<People>() {

            @Override
            public People create(long _id, String name, String email, String phone, String seeking,
                                 String resume, String github, String linkedin, String motto,
                                 String objective) {

                return new AutoValue_Resume_People(_id, name, email, phone, seeking, resume, github,
                        linkedin, motto, objective);
            }
        });

        public static final RowMapper<People> PEOPLE_MAPPER = FACTORY.get_all_personsMapper();
        public static final RowMapper<People> PERSON_MAPPER = FACTORY.get_personMapper();
    }

    @AutoValue
    public abstract static class Contribution implements ContributionModel, ModelIndicator {

        public static final Factory<Contribution> FACTORY = new Factory<>(new Creator<Contribution>() {

            @Override
            public Contribution create(long _id, String project, String creator,
                                       String year, String description, String uri) {

                return new AutoValue_Resume_Contribution(_id, project, creator, year,
                        description, uri);
            }
        });

        public static final RowMapper<Contribution> MAPPER = FACTORY.get_all_contributionsMapper();

    }

//	private static final class Adapter {
//
//		private static final ColumnAdapter<Uri> URI = new ColumnAdapter<Uri>() {
//
//			@Override
//			public Uri map(Cursor cursor, int columnIndex) {
//				return Uri.parse(cursor.getString(columnIndex));
//			}
//
//			@Override
//			public void marshal(ContentValues values, String key, Uri value) {
//				values.put(key, value.toString());
//			}
//		};
//
//	}

}