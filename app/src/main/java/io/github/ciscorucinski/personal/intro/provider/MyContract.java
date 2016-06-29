package io.github.ciscorucinski.personal.intro.provider;

import android.support.annotation.NonNull;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;
import net.simonvt.schematic.annotation.Table;

import io.github.ciscorucinski.personal.intro.model.ContributionModel;
import io.github.ciscorucinski.personal.intro.model.EducationModel;
import io.github.ciscorucinski.personal.intro.model.PeopleModel;
import io.github.ciscorucinski.personal.intro.model.ProjectsModel;
import io.github.ciscorucinski.personal.intro.model.SkillsModel;
import io.github.ciscorucinski.personal.intro.model.WorkModel;

import static net.simonvt.schematic.annotation.DataType.Type.INTEGER;
import static net.simonvt.schematic.annotation.DataType.Type.TEXT;

//@Database(className = MyContract.DB_CLASS_NAME,
//          fileName = MyContract.DB_FILE,
//          version = MyContract.VERSION)
public final class MyContract {

    @Table(ContributionColumns.class)
    public static final String Contribution = ContributionColumns.TABLE_NAME;
    @Table(EducationColumns.class)
    public static final String Education = EducationColumns.TABLE_NAME;
    @Table(ProjectColumns.class)
    public static final String Project = ProjectColumns.TABLE_NAME;
    @Table(PeopleColumns.class)
    public static final String People = PeopleColumns.TABLE_NAME;
    @Table(SkillColumns.class)
    public static final String Skill = SkillColumns.TABLE_NAME;
    @Table(WorkColumns.class)
    public static final String Work = WorkColumns.TABLE_NAME;

    public interface ProjectColumns {
        String TABLE_NAME = ProjectsModel.TABLE_NAME;

        @DataType(INTEGER)
        @PrimaryKey
        @AutoIncrement
        String ID = "_id";
        @DataType(TEXT)
        @NotNull
        String NAME = ProjectsModel.NAME;
        @DataType(TEXT)
        @NotNull
        String LINK = ProjectsModel.LINK;
        @DataType(TEXT)
        @NotNull
        String LOCATION = ProjectsModel.LOCATION;
        @DataType(TEXT)
        @NotNull
        String YEAR = ProjectsModel.YEAR;
        @DataType(TEXT)
        @NotNull
        String DESCRIPTION = ProjectsModel.DESCRIPTION;
        @DataType(TEXT)
        @NotNull
        String ACCOMPLISHMENT1 = ProjectsModel.ACCOMPLISHMENT1;
        @DataType(TEXT)
        @NotNull
        String ACCOMPLISHMENT2 = ProjectsModel.ACCOMPLISHMENT2;
        @DataType(TEXT)
        @NotNull
        String ACCOMPLISHMENT3 = ProjectsModel.ACCOMPLISHMENT3;
    }

    public interface WorkColumns {
        String TABLE_NAME = WorkModel.TABLE_NAME;

        @DataType(INTEGER)
        @PrimaryKey
        @AutoIncrement
        String ID = "_id";
        @DataType(TEXT)
        @NotNull
        String NAME = WorkModel.NAME;
        @DataType(TEXT)
        @NotNull
        String LOCATION = WorkModel.LOCATION;
        @DataType(TEXT)
        @NotNull
        String POSITION = WorkModel.POSITION;
        @DataType(TEXT)
        @NotNull
        String START_DATE = WorkModel.START_DATE;
        @DataType(TEXT)
        @NotNull
        String END_DATE = WorkModel.END_DATE;
    }

    public interface SkillColumns {
        String TABLE_NAME = SkillsModel.TABLE_NAME;

        @DataType(INTEGER)
        @PrimaryKey
        @AutoIncrement
        String ID = "_id";
        @DataType(TEXT)
        @NotNull
        String TYPE = SkillsModel.TYPE;
        @DataType(TEXT)
        @NotNull
        String LEVEL_1_SKILLS = SkillsModel.LEVEL_1_SKILLS;
        @DataType(TEXT)
        @NotNull
        String LEVEL_2_SKILLS = SkillsModel.LEVEL_2_SKILLS;
        @DataType(TEXT)
        @NotNull
        String LEVEL_3_SKILLS = SkillsModel.LEVEL_3_SKILLS;
    }

    public interface PeopleColumns {
        String TABLE_NAME = PeopleModel.TABLE_NAME;

        @DataType(INTEGER)
        @PrimaryKey
        @AutoIncrement
        String ID = "_id";
        @DataType(TEXT)
        @NotNull
        String NAME = PeopleModel.NAME;
        @DataType(TEXT)
        @NotNull
        String EMAIL = PeopleModel.EMAIL;
        @DataType(TEXT)
        @NotNull
        String PHONE = PeopleModel.PHONE;
        @DataType(TEXT)
        @NonNull
        String SEEKING = PeopleModel.SEEKING_POSITION;
        @DataType(TEXT)
        @NotNull
        String RESUME = PeopleModel.RESUME;
        @DataType(TEXT)
        @NotNull
        String GITHUB = PeopleModel.GITHUB;
        @DataType(TEXT)
        @NotNull
        String LINKEDIN = PeopleModel.LINKEDIN;
        @DataType(TEXT)
        @NotNull
        String MOTTO = PeopleModel.MOTTO;
        @DataType(TEXT)
        @NotNull
        String OBJECTIVE = PeopleModel.OBJECTIVE;
    }

    public interface EducationColumns {
        String TABLE_NAME = EducationModel.TABLE_NAME;

        @DataType(INTEGER)
        @PrimaryKey
        @AutoIncrement
        String ID = "_id";
        @DataType(TEXT)
        @NotNull
        String NAME = EducationModel.NAME;
        @DataType(TEXT)
        @NotNull
        String DEGREE = EducationModel.DEGREE;
        @DataType(TEXT)
        @NotNull
        String LOCATION = EducationModel.LOCATION;
        @DataType(TEXT)
        @NotNull
        String START_DATE = EducationModel.START_DATE;
        @DataType(TEXT)
        @NotNull
        String END_DATE = EducationModel.END_DATE;
        @DataType(TEXT)
        @NotNull
        String COURSES = EducationModel.COURSES;
    }

    public interface ContributionColumns {
        String TABLE_NAME = ContributionModel.TABLE_NAME;

        @DataType(INTEGER)
        @PrimaryKey
        @AutoIncrement
        String ID = "_id";
        @DataType(TEXT)
        @NotNull
        String PROJECT = ContributionModel.PROJECT;
        @DataType(TEXT)
        @NotNull
        String CREATOR = ContributionModel.CREATOR;
        @DataType(TEXT)
        @NotNull
        String YEAR = ContributionModel.YEAR;
        @DataType(TEXT)
        @NotNull
        String DESCRIPTION = ContributionModel.DESCRIPTION;
        @DataType(TEXT)
        @NotNull
        String URI = ContributionModel.URI;
    }
}