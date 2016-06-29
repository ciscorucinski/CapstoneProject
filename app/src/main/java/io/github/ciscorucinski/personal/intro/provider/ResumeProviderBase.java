//package io.github.ciscorucinski.personal.intro.provider;
//
//import android.net.Uri;
//
//import net.simonvt.schematic.annotation.ContentProvider;
//import net.simonvt.schematic.annotation.ContentUri;
//import net.simonvt.schematic.annotation.InexactContentUri;
//import net.simonvt.schematic.annotation.TableEndpoint;
//
//@ContentProvider(name = ResumeProviderBase.CONCRETE_CLASS_NAME,
//                 authority = ResumeProvider.AUTHORITY,
//                 database = ResumeDatabase.class)
//public final class ResumeProviderBase {
//
//	public static final String CONCRETE_CLASS_NAME = "ResumeProvider";
//
//	public static final String AUTHORITY = "io.github.ciscorucinski.personal.intro";
//	private static final String CONTENT = "content://";
//
//	private static final String URI = "vnd.android.cursor.dir/io.github.ciscorucinski.personal.intro.content.models.";
//
//	private static final Uri BASE_CONTENT_URI = Uri.parse(CONTENT + AUTHORITY);
//
//	private interface Path {
//
//		String CONTRIBUTIONS = MyContract.Contribution;
//		String EDUCATION = MyContract.Education;
//		String PEOPLE = MyContract.People;
//		String PROJECTS = MyContract.Project;
//		String SKILLS = MyContract.Skill;
//		String WORK = MyContract.Work;
//
//	}
//
//	private static Uri buildUri(String ... paths) {
//
//		Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
//
//		for (String path: paths) {
//			builder.appendPath(path);
//		}
//
//		return builder.build();
//
//	}
//
//	@TableEndpoint(table = MyContract.Contribution)
//	public static final class Contribution {
//
//		@ContentUri(
//				path = Path.CONTRIBUTIONS,
//				type = "vnd.android.cursor.dir/" + Path.CONTRIBUTIONS)
//		public static final Uri CONTENT_URI = buildUri(Path.CONTRIBUTIONS);
//
//	}
//
//	@TableEndpoint(table = MyContract.Education)
//	public static final class Education {
//
//		@ContentUri(
//				path = Path.EDUCATION,
//				type = "vnd.android.cursor.dir/" + Path.EDUCATION)
//		public static final Uri CONTENT_URI = buildUri(Path.EDUCATION);
//
//	}
//
//	@TableEndpoint(table = MyContract.Project)
//	public static final class Project {
//
//		@ContentUri(
//				path = Path.PROJECTS,
//				type = "vnd.android.cursor.dir/" + Path.PROJECTS)
//		public static final Uri CONTENT_URI = buildUri(Path.PROJECTS);
//
//	}
//
//	@TableEndpoint(table = MyContract.Skill)
//	public static final class Skill {
//
//		@ContentUri(
//				path = Path.SKILLS,
//				type = "vnd.android.cursor.dir/" + Path.SKILLS)
//		public static final Uri CONTENT_URI = buildUri(Path.SKILLS);
//
//	}
//
//	@TableEndpoint(table = MyContract.Work)
//	public static final class Work {
//
//		@ContentUri(
//				path = Path.WORK,
//				type = "vnd.android.cursor.dir/" + Path.WORK)
//		public static final Uri CONTENT_URI = buildUri(Path.WORK);
//
//	}
//
//	@TableEndpoint(table = MyContract.People)
//	public static final class Person {
//
//		@InexactContentUri(
//				path = Path.PEOPLE + "/#",
//				name = Path.PEOPLE + "_ID",
//				type = "vnd.android.cursor.item/" + Path.PEOPLE,
//				whereColumn = MyContract.PeopleColumns.ID,
//				pathSegment = 1)
//		public static Uri withId(long id) {
//
//			return buildUri(Path.PEOPLE, String.valueOf(id));
//
//		}
//
//	}
//}