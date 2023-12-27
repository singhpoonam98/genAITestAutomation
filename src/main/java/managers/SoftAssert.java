package managers;


import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.Reporter;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

public final class SoftAssert extends Assertion {
	private static Logger logg = (Logger) LogManager.getLogger(SoftAssert.class);
	// LinkedHashMap to preserve the order
	private Map<AssertionError, IAssert> allErrors = Maps.newLinkedHashMap();
	@Override
	protected void doAssert(IAssert assertCommand) {
		onBeforeAssert(assertCommand);
		try {
			executeAssert(assertCommand);
		} finally {
			onAfterAssert(assertCommand);
		}
	}
	@Override
	public void onAssertSuccess(IAssert assertCommand) {
		showAssertInfo(assertCommand, (AssertionError) null, false);
	}
	@Override
	public void onAssertFailure(IAssert assertCommand, AssertionError ex) {
		showAssertInfo(assertCommand, ex, true);
	}
	@Override
	public void executeAssert(IAssert a) {
		try {
			a.doAssert();
			onAssertSuccess(a);
		} catch (AssertionError ex) {
			onAssertFailure(a, ex);
			this.allErrors.put(ex, a);
		}
	}
	private void showAssertInfo(IAssert assertCommand, AssertionError ex, boolean failedTest) {
		StringBuilder sb = new StringBuilder();
		sb.append("Soft Assert ");
		if (assertCommand.getMessage() != null && !assertCommand.getMessage().trim().isEmpty())
			sb.append("[").append(assertCommand.getMessage()).append("] ");
		if (failedTest) {
			sb.append("failed.").append(" Expected: " + assertCommand.getExpected() + " but Actual: " + assertCommand.getActual() + "<br/>");
			sb.append("<details style='padding-inline-start:1%;'><summary>Click to trace the error</summary><p><i>" + ExceptionUtils.getStackTrace(ex) + "</i></p></details>");
			Report.fail(logg, sb.toString());
		} else {
			sb.append("passed.").append(" Expected: " + assertCommand.getExpected() + " and Actual: " + assertCommand.getActual() + "<br/>");
			Report.pass(logg, sb.toString());
		}
	}
	public void assertAll() {
		if (this.allErrors.isEmpty()) {
			return;
		} else {
			allErrors.clear();
			if (Reporter.getCurrentTestResult() != null) {
				Reporter.getCurrentTestResult().setStatus(2);
				Reporter.getCurrentTestResult().getStatus();
			}
		}
	}
}
